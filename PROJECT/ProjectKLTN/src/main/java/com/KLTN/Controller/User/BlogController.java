package com.KLTN.Controller.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.KLTN.Entity.Blog;
import com.KLTN.Entity.Product;
import com.KLTN.Entity.User;
import com.KLTN.Model.BestSellerModel;
import com.KLTN.Model.ShowProduct;
import com.KLTN.Service.BlogService;
import com.KLTN.Service.CommentService;
import com.KLTN.Service.OrderService;
import com.KLTN.Service.ParamService;
import com.KLTN.Service.ProductService;
import com.KLTN.Service.UserService;

@Controller
public class BlogController {
	@Autowired
	private BlogService blogService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;

	@Autowired
	private ParamService paramService;

	// Sử dụng PageRequest để phân trang blog, mặc định là trang đầu tiên với 8 blog
	// mỗi trang.
	// Sau đó truyền blogList vào model để hiển thị trên trang blog_index.
	@GetMapping("/blog")
	public String index(Model model, @RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0), 8);
		Page<Blog> list = blogService.findAllBlogActive(pageable);

		for (Blog blog : list) {
			String uploadDay = paramService.convertDate(blog.getUploadday());
			blog.setUploadday(uploadDay);
		}

		model.addAttribute("blogList", list);
		return "user/blog/blog_index";
	}

	// Lấy danh sách các sản phẩm giảm giá từ productService.
	// Tính tổng số sao đánh giá của mỗi sản phẩm và lưu vào đối tượng ShowProduct.
	// Trả về danh sách ShowProduct để được thêm vào model với tên listSale.
	@ModelAttribute("listSale")
	public List<ShowProduct> getListProductSale(Model model) {
		List<Product> list = productService.getListProductSales();
		List<ShowProduct> listProduct = new ArrayList<ShowProduct>();

		for (Product product : list) {
			ShowProduct showProduct = new ShowProduct();
			int totalStar = commentService.getAllStarCommentByProductNameSearch(product.getNamesearch());
			showProduct.setProduct(product);
			showProduct.setTotalStar(totalStar);
			listProduct.add(showProduct);
		}

		return listProduct;
	}

	// Lấy 4 sản phẩm bán chạy nhất từ orderService.
	// Trả về danh sách ShowProduct để được tự động thêm vào model với tên
	// listBestSeller.
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

	// Tìm blog theo nameSearch.
	// Lấy thông tin người dùng tạo blog dựa trên ID người tạo.
	// Chuyển định dạng ngày đăng tải của blog thành chuỗi.
	// Truyền blog và người tạo blog vào model để hiển thị trên trang blog_detail.
	@GetMapping("/blog/{nameSearch}")
	public String index(Model model, @PathVariable("nameSearch") String nameSearch) {
		Blog blog = blogService.findBlogByNameSearch(nameSearch);
		User user = userService.findById(blog.getPersoncreate());
		String uploadDay = paramService.convertDate(blog.getUploadday());

		blog.setUploadday(uploadDay);
		System.out.println(blog.getUploadday());
		model.addAttribute("blogInfor", blog);
		model.addAttribute("blogUser", user);
		return "user/blog/blog_detail";
	}

}
