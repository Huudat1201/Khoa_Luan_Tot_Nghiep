package com.KLTN.Controller.User;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.KLTN.Entity.Discount;
import com.KLTN.Model.AlertModel;
import com.KLTN.Model.BestSellerModel;
import com.KLTN.Model.CartModel;
import com.KLTN.Model.ProductModel;
import com.KLTN.Model.ShowProduct;
import com.KLTN.Service.CommentService;
import com.KLTN.Service.DiscountService;
import com.KLTN.Service.OrderService;
import com.KLTN.Service.ParamService;
import com.KLTN.Service.ProductService;
import com.KLTN.Service.SessionService;
import com.KLTN.Service.Impl.ShoppingCartServiceImpl;

@Controller
public class CartController {

	@Autowired
	private ShoppingCartServiceImpl cartService;

	@Autowired
	private ProductService productService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private DiscountService discountService;

	@Autowired
	SessionService sessionService;

	@Autowired
	private ParamService paramService;

	// Xóa mã giảm giá trong giỏ hàng và khởi tạo đối tượng AlertModel để hiển thị
	// cảnh báo.
	// Gán đối tượng giỏ hàng (cartService) vào model để sử dụng trong trang
	// shopping_cart.
	@GetMapping("/shop/cart")
	public String index(Model model) {
		model.addAttribute("showDiscount", false);

		cartService.clearDiscount();
		model.addAttribute("cart", cartService);

		AlertModel alertModel = new AlertModel();
		model.addAttribute("alertModel", alertModel);

		return "user/cart/shopping_cart";
	}

	// Kiểm tra nếu số lượng yêu cầu không vượt quá số lượng hiện có, cập nhật giỏ
	// hàng.
	// Nếu số lượng vượt quá, thông báo lỗi sẽ được gửi qua redirectAttributes.
	@PostMapping("/cart/update/{id}")
	public String update(@PathVariable("id") Integer id, HttpServletRequest req,
			RedirectAttributes redirectAttributes) {
		String qty = req.getParameter("quantity");
		ProductModel product = productService.getOneProductById(id);

		if (Integer.parseInt(qty) <= product.getQuality()) {
			cartService.update(id, Integer.parseInt(qty));
		} else {
			redirectAttributes.addFlashAttribute("error",
					"Số lượng yêu cầu cho " + Integer.parseInt(qty) + " vượt quá số lượng hiện có.");
		}

		return "redirect:/shop/cart";
	}

	// Mô tả: Phương thức GET chuyển hướng đến trang giỏ hàng.
	// Chức năng: Dùng để đảm bảo người dùng không truy cập trực tiếp đến phương
	// thức POST của giảm giá.
	@GetMapping("/shop/cart/discount")
	public String getDiscount() {
		return "redirect:/shop/cart";
	}

	// Kiểm tra mã giảm giá: Nếu mã hợp lệ và tổng giá trị giỏ hàng đạt điều kiện,
	// mã sẽ được áp dụng.
	// Nếu không hợp lệ, cảnh báo sẽ hiển thị thông báo lỗi.
	@PostMapping("/shop/cart/discount")
	public String discount(Model model) {
		String code = paramService.getString("discount", "");

		Discount discount = discountService.getDiscountByCode(code);

		AlertModel alertModel = new AlertModel();

		if (discount == null) {
			cartService.clearDiscount();
			cartService.getAmount();
			alertModel.setAlert("alert-warning");
			alertModel.setContent("Mã giảm giá không tồn tại!");
			alertModel.setDisplay(true);
		}

		else {
			if (cartService.getAmount() >= discount.getMoneylimit()) {
				cartService.addDiscount(discount.getId(), discount);
				cartService.getAmount();
				alertModel.setAlert("alert-success");
				alertModel.setContent("Bạn đã áp dụng mã giảm giá thành công!");
				alertModel.setDisplay(true);
			} else {
				cartService.clearDiscount();
				cartService.getAmount();
				alertModel.setAlert("alert-warning");
				alertModel.setContent("Mã giảm giá không tồn tại!");
				alertModel.setDisplay(true);
			}
		}

		model.addAttribute("showDiscount", true);
		model.addAttribute("discount", code);
		model.addAttribute("alertModel", alertModel);

		model.addAttribute("cart", cartService);
		return "user/cart/shopping_cart";
	}

	// Mô tả: Tính tổng số tiền của giỏ hàng.
	// Chức năng: Duyệt qua từng sản phẩm trong giỏ hàng và tính tổng giá trị dựa
	// trên giá và số lượng.
	@ModelAttribute("total")
	public int total() {
		List<CartModel> list = new ArrayList<>(cartService.getItems());
		int total = 0;
		for (CartModel i : list) {
			total = total + i.getProduct().getPrice() * i.getQuality();
		}
		return total;
	}



	// Tạo danh sách sản phẩm bán chạy, tính tổng số sao đánh giá, và trả về danh
	// sách ShowProduct
	// để hiển thị trong model với tên listBestSeller.
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
	
	// Xóa sản phẩm dựa trên ID khỏi giỏ hàng, cập nhật session và chuyển hướng đến
	// trang giỏ hàng.
	@GetMapping("/cart/remove/{id}")
	public String remove(@PathVariable("id") Integer id, HttpSession session) {
		cartService.remove(id);
		session.setAttribute("sessionProduct", cartService);
		return "redirect:/shop/cart";
	}
	
}
