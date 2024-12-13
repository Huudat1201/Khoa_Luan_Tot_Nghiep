package com.KLTN.Controller.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.KLTN.Entity.Favorite;
import com.KLTN.Entity.Order;
import com.KLTN.Model.AlertModel;
import com.KLTN.Model.OrderModel;
import com.KLTN.Service.FavoriteService;
import com.KLTN.Service.OrderService;
import com.KLTN.Service.ParamService;
import com.KLTN.Service.UserService;
import com.KLTN.Service.Impl.ShoppingCartServiceImpl;

@Controller
public class AccountController {

	@Autowired
	private FavoriteService favoriteService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private ParamService paramService;

	@Autowired
	ShoppingCartServiceImpl cartService;

	@Autowired
	UserService userService;

	// giao diện chính của trang tài khoản người dùng.
	@GetMapping("/account")
	public String index() {
		return "user/account/account_index";
	}

	// giao diện chứa thông tin cá nhân của người dùng.
	@GetMapping("/account/information")
	public String information(Model model) {
		return "user/account/account_information";
	}

	// Lấy danh sách lịch sử đơn hàng và áp dụng giảm giá (nếu có), rồi truyền vào
	// model
	// để hiển thị trên trang account_order.
	@GetMapping("/account/order")
	public String order(Model model) {
		List<OrderModel> listOrderHistory = orderService.listOrderHistory();
		for (OrderModel list : listOrderHistory) {
			Order order = orderService.getOrderByName(list.getId()).get(0);
			if (order != null) {
				list.setDiscount(order.getDiscount());
			}
		}
		model.addAttribute("listOrder", listOrderHistory);
		return "user/account/account_order";
	}

	// Lấy thông tin chi tiết của đơn hàng với mã id, tính tổng tiền và giảm giá
	// (nếu có).
	// Nếu không tìm thấy, trả về trang 404. Nếu tìm thấy, hiển thị thông tin trên
	// trang account_invoice.
	@GetMapping("/account/order/invoice/{id}")
	public String invoice(@PathVariable("id") String id, Model model) {
		List<Order> list = orderService.listOrderByCodeAndUsername(id);
		if (list.isEmpty()) {
			return "user/security/404page";
		} else {
			int total = 0;
			int discount = 0;
			for (Order order : list) {
				total = total + order.getProduct().getPrice() * order.getQuality();
			}
			if (list.get(0).getDiscount() != null) {
				discount = list.get(0).getDiscount().getPrice();
			}
			model.addAttribute("listProduct", list);
			model.addAttribute("total", total);
			model.addAttribute("discount", discount);
		}
		return "user/account/account_invoice";
	}

	// Lấy danh sách sản phẩm yêu thích của người dùng và truyền vào model để hiển
	// thị trên trang account_favorite.
	@GetMapping("/account/favorite")
	public String favorite(Model model) {
		List<Favorite> listFavorite = favoriteService.getListFavoriteByEmail();
		model.addAttribute("listFavorite", listFavorite);
		return "user/account/account_favorite";
	}

	// Xoá sản phẩm yêu thích có id chỉ định rồi chuyển hướng lại trang
	// account_favorite.
	@GetMapping("/account/favorite/delete/{id}")
	public String deleteFavorite(@PathVariable("id") int id, Model model) {
		favoriteService.delete(id);
		return "redirect:/account/favorite";
	}

	// Trả về trang account_order_search để người dùng nhập mã đơn hàng muốn tìm
	// kiếm,
	// kèm theo một đối tượng alertModel để hiển thị thông báo.
	@GetMapping("/account/order/search")
	public String search(Model model) {
		AlertModel alertModel = new AlertModel();
		model.addAttribute("alertModel", alertModel);
		return "user/account/account_order_search";
	}

	// Hủy đơn hàng với mã id rồi chuyển hướng lại trang account_order.
	@GetMapping("/account/order/cancel/{id}")
	public String cancel(@PathVariable("id") String id, Model model) {
		orderService.cancelOrder(id);
		return "redirect:/account/order";
	}

	// Nhận mã đơn hàng từ paramService. Nếu mã rỗng, hiển thị cảnh báo yêu cầu nhập
	// mã đơn hàng.
	// Nếu mã không tồn tại, hiển thị cảnh báo không tìm thấy mã đơn hàng.
	// Nếu tồn tại, chuyển hướng đến trang chi tiết account_invoice.
	@PostMapping("/account/order/search")
	public String searchByCode(Model model) {
		AlertModel alertModel = new AlertModel();
		String code = paramService.getString("code", "");

		if (code.trim().isEmpty()) {
			alertModel.setAlert("alert-warning");
			alertModel.setContent("Vui lòng nhập mã đơn hàng!");
			alertModel.setDisplay(true);
		} else {
			List<Order> list = orderService.listOrderByCodeAndUsername(code);
			if (list.isEmpty()) {
				alertModel.setAlert("alert-warning");
				alertModel.setContent("Mã đơn hàng không tồn tại!");
				alertModel.setDisplay(true);
			} else {
				return "redirect:/account/order/invoice/" + code;
			}
		}

		model.addAttribute("code", code.trim());
		model.addAttribute("alertModel", alertModel);
		return "user/account/account_order_search";
	}

}
