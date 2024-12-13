package com.KLTN.Controller.User;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.KLTN.Entity.Blog;
import com.KLTN.Entity.Manufacturer;
import com.KLTN.Entity.Product;
import com.KLTN.Model.BestSellerModel;
import com.KLTN.Model.ShowProduct;
import com.KLTN.Service.BlogService;
import com.KLTN.Service.CommentService;
import com.KLTN.Service.FavoriteService;
import com.KLTN.Service.ManufacturerService;
import com.KLTN.Service.OrderService;
import com.KLTN.Service.ParamService;
import com.KLTN.Service.ProductService;
import com.KLTN.Service.UserRoleService;

@Controller
public class IndexController {

	@Autowired
	UserRoleService userRoleService;

	@Autowired
	private ProductService productService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private FavoriteService favoriteService;

	@Autowired
	private ManufacturerService manufacturerService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private BlogService blogService;

	@Autowired
	private ParamService paramService;

	// xử lý yêu cầu đến trang chủ và trả về trang user/home/index.
	@GetMapping({ "/", "/index" })
	public String index(Model model) {
		return "user/home/index";
	}

	// Lấy danh sách tất cả các hãng sản xuất từ manufacturerService và trả về để
	// hiển thị.
	@ModelAttribute("manufacturer")
	public List<Manufacturer> manufacture(Model model) {
		List<Manufacturer> list = manufacturerService.findAll();
		return list;
	}

	// Lấy danh sách sản phẩm mới nhất từ productService.
	// Tạo các đối tượng ShowProduct, bao gồm thông tin sản phẩm và tổng số sao của
	// sản phẩm từ commentService.
	// Chia danh sách thành từng nhóm nhỏ có 2 sản phẩm để hiển thị theo cấu trúc cụ
	// thể trên giao diện.
	@ModelAttribute("latestProduct")
	public List<List<ShowProduct>> getLatestProduct(Model model) {
		List<Product> list = productService.getListLatestProduct();

		List<ShowProduct> temp = new ArrayList<>();

		List<List<ShowProduct>> result = new ArrayList<List<ShowProduct>>();

		for (int i = 0; i < list.size(); i++) {
			int totalStar = commentService.getAllStarCommentByProductNameSearch(list.get(i).getNamesearch());

			ShowProduct showProduct = new ShowProduct();
			showProduct.setProduct(list.get(i));
			showProduct.setTotalStar(totalStar);
			temp.add(showProduct);
			if (i % 2 != 0) {
				result.add(temp);
				temp = new ArrayList<>();
			}
			if (i == (list.size() - 1)) {
				if (i % 2 == 0) {
					result.add(temp);
					temp = new ArrayList<>();
				}
			}
		}

		return result;
	}

	// Lấy danh sách sản phẩm được xem nhiều nhất từ productService.
	// Tạo các đối tượng ShowProduct với thông tin sản phẩm và tổng số sao, sau đó
	// trả về để hiển thị trên trang.
	@ModelAttribute("viewsProduct")
	public List<ShowProduct> getViewsProduct(Model model) {
		List<Product> list = productService.getListViewsProduct();
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

	// Lấy danh sách 6 bài blog mới nhất từ blogService.
	// Chuyển đổi định dạng ngày của từng bài blog bằng paramService để hiển thị cho
	// người dùng.
	@ModelAttribute("listBlog")
	public List<Blog> getListBlog(Model model) {
		List<Blog> listBlog = blogService.getSixBlog();
		for (Blog blog : listBlog) {
			String uploadDay = paramService.convertDate(blog.getUploadday());
			blog.setUploadday(uploadDay);
		}
		return listBlog;
	}

	// Lấy danh sách sản phẩm giảm giá từ productService.
	// Tạo các đối tượng ShowProduct với thông tin sản phẩm và tổng số sao, sau đó
	// trả về để hiển thị trên trang.
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

	// Lấy danh sách 4 sản phẩm bán chạy nhất từ orderService.
	// Tạo các đối tượng ShowProduct với thông tin sản phẩm và tổng số sao, sau đó
	// trả về để hiển thị trong mục "Bán chạy nhất" trên trang.
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

	// Lấy danh sách 4 sản phẩm yêu thích nhất từ favoriteService.
	// Tạo các đối tượng ShowProduct với thông tin sản phẩm và tổng số sao, sau đó
	// trả về để hiển thị.
	@ModelAttribute("listFavorite")
	public List<ShowProduct> demo(Model model) {
		Pageable topFour = PageRequest.of(0, 4);
		List<BestSellerModel> list = favoriteService.getListBestSellerProduct(topFour);

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
