package com.KLTN.Controller.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.KLTN.Entity.Product;
import com.KLTN.Model.BestSellerModel;
import com.KLTN.Model.CartModel;
import com.KLTN.Model.ShowProduct;
import com.KLTN.Service.CategoryService;
import com.KLTN.Service.CommentService;
import com.KLTN.Service.OrderService;
import com.KLTN.Service.ProductService;
import com.KLTN.Service.SessionService;
import com.KLTN.Service.Impl.ShoppingCartServiceImpl;

@Controller
public class DetailProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private SessionService sessionService;

	@Autowired
	private ShoppingCartServiceImpl cartService;

	// Tăng lượt xem cho sản phẩm bằng cách gọi productService.updateView.
	// Đặt thuộc tính infor trong model để kiểm soát hiển thị thông tin trên trang
	// chi tiết sản phẩm.
	// Lưu giỏ hàng vào session thông qua sessionService.
	// Trả về trang "user/detail/product_detail".
	@GetMapping("/san-pham/{nameSearch}")
	public String index(@PathVariable("nameSearch") String nameSearch, Model model) {
		productService.updateView(nameSearch);
		model.addAttribute("infor", false);
		sessionService.set("sessionProduct", cartService);
		return "user/detail/product_detail";
	}

	// Xử lý logic thêm sản phẩm vào giỏ hàng.
	// Kiểm tra số lượng sản phẩm yêu cầu có sẵn không. Nếu đủ, thêm sản phẩm vào
	// giỏ hàng; nếu không, thông báo lỗi cho người dùng.
	// Nếu hành động là "buy-now", chuyển hướng người dùng đến trang giỏ hàng với
	// tổng tiền các sản phẩm trong giỏ.
	// Nếu không, trả về trang chi tiết sản phẩm.
	@SuppressWarnings("static-access")
	@PostMapping("/san-pham/{nameSearch}")
	public String orderProduct(@PathVariable("nameSearch") String nameSearch, @RequestParam("quantity") int quantity,
			@RequestParam("action") String action, Model model, RedirectAttributes redirectAttributes) {
		Product product = productService.getProductByNameSearch(nameSearch);

		// Kiểm tra số lượng sản phẩm có đủ để thêm vào giỏ hàng không
		if (quantity <= product.getQuality()) {
			Map<Integer, CartModel> map = cartService.map;
			CartModel cartModel = map.get(product.getId());

			if (cartModel == null) {
				cartModel = new CartModel();
				cartModel.setId(product.getId());
				cartModel.setProduct(product);
				cartModel.setQuality(quantity);
				cartService.add(product.getId(), cartModel);
			} else {
				cartService.update(cartModel.getId(), cartModel.getQuality() + quantity);
			}

			model.addAttribute("infor", true);
			model.addAttribute("quantity", quantity);
			sessionService.set("sessionProduct", cartService);
		} else {
			redirectAttributes.addFlashAttribute("error",
					"Số lượng yêu cầu cho " + quantity + " vượt quá số lượng hiện có.");
			return "redirect:/san-pham/" + nameSearch;
		}

		if (action.equals("buy-now")) {
			List<CartModel> list = new ArrayList<>(cartService.getItems());
			int total = 0;
			for (CartModel i : list) {
				total = total + i.getProduct().getPrice() * i.getQuality();
			}
			model.addAttribute("total", total);
			return "redirect:/shop/cart";
		}
		return "user/detail/product_detail";
	}

	// Lấy thông tin chi tiết của sản phẩm (thông qua
	// productService.getProductByNameSearch) để hiển thị trên trang.
	@ModelAttribute("inforProduct")
	public Product inforCategory(@PathVariable("nameSearch") String nameSearch) {
		Product product = productService.getProductByNameSearch(nameSearch);
		return product;
	}

	// Tạo danh sách các sản phẩm liên quan dựa trên danh mục của sản phẩm hiện tại.
	// Đưa thêm thông tin số sao của từng sản phẩm vào ShowProduct.
	@ModelAttribute("listProductRelated")
	public List<ShowProduct> listProductRelated(@PathVariable("nameSearch") String nameSearch) {
		Product product = productService.getProductByNameSearch(nameSearch);
		List<Product> list = productService.getListProductRelated(product.getCategory().getId());

		List<ShowProduct> listProduct = new ArrayList<ShowProduct>();

		for (Product item : list) {
			ShowProduct showProduct = new ShowProduct();
			int totalStar = commentService.getAllStarCommentByProductNameSearch(item.getNamesearch());
			showProduct.setProduct(item);
			showProduct.setTotalStar(totalStar);
			listProduct.add(showProduct);
		}

		return listProduct;
	}

	// Đếm số bình luận của sản phẩm hiện tại và trả về kết quả để hiển thị trên
	// trang.
	@ModelAttribute("countComment")
	public int countComment(@PathVariable("nameSearch") String nameSearch) {
		int result = commentService.getCountCommentByProductNameSearch(nameSearch);
		return result;
	}

	// Tính tổng số sao của sản phẩm dựa trên tất cả các bình luận.
	@ModelAttribute("totalStar")
	public int totalStar(@PathVariable("nameSearch") String nameSearch) {
		int result = commentService.getAllStarCommentByProductNameSearch(nameSearch);
		return result;
	}

	// Lấy danh sách các sản phẩm bán chạy nhất và chuyển đổi thành danh sách
	// ShowProduct, có thêm thông tin về số sao cho từng sản phẩm để hiển thị.
	@ModelAttribute("listBestSeller")
	public List<ShowProduct> getListBestSeller(Model model) {
		Pageable topFour = PageRequest.of(0, 4);

		List<BestSellerModel> list = orderService.getListBestSellerProduct(topFour);

		List<ShowProduct> listProduct = new ArrayList<ShowProduct>();

		for (BestSellerModel bestSeller : list) {
			ShowProduct showProduct = new ShowProduct();
			int totalStar = commentService
					.getAllStarCommentByProductNameSearch(bestSeller.getProduct().getNamesearch());
			showProduct.setProduct(bestSeller.getProduct());
			showProduct.setTotalStar(totalStar);
			listProduct.add(showProduct);
		}
		return listProduct;
	}
}