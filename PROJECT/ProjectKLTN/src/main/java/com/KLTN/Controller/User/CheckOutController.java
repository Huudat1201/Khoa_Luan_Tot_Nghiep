package com.KLTN.Controller.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.KLTN.Entity.Address;
import com.KLTN.Entity.Discount;
import com.KLTN.Entity.Order;
import com.KLTN.Entity.Product;
import com.KLTN.Model.CartModel;
import com.KLTN.Service.AddressService;
import com.KLTN.Service.DiscountService;
import com.KLTN.Service.OrderService;
import com.KLTN.Service.ParamService;
import com.KLTN.Service.ProductService;
import com.KLTN.Service.SessionService;
import com.KLTN.Service.Impl.MailerServiceImpl;
import com.KLTN.Service.Impl.ShoppingCartServiceImpl;

@Controller
public class CheckOutController {

	public Model model1;
	public String addressId1;
	public String method1;
	public String comment1;

	@Autowired
	AddressService addressService;

	@Autowired
	AddressService provinceService;

	@Autowired
	ShoppingCartServiceImpl cartService;

	@Autowired
	DiscountService discountService;

	@Autowired
	SessionService sessionService;

	@Autowired
	ParamService paramService;

	@Autowired
	OrderService orderService;

	@Autowired
	ProductService productService;

	@Autowired
	MailerServiceImpl mailerService;

	// Kiểm tra giỏ hàng của người dùng, nếu giỏ hàng trống thì chuyển hướng về
	// trang giỏ hàng, nếu không thì hiển thị form thanh toán.
	@GetMapping("/shop/cart/checkout")
	public String index(Model model) {
		List<CartModel> listCartModel = new ArrayList<>(cartService.getItems());
		if (listCartModel.isEmpty()) {
			return "redirect:/shop/cart";
		}
		model.addAttribute("cart", cartService);
		return "user/checkout/checkout_form";
	}

	// Lấy các thông tin thanh toán như địa chỉ, phương thức giao hàng và ghi chú.
	// để tiến hành thanh toán.
	@PostMapping("/shop/cart/checkout")
	public String order(HttpServletRequest request, Model model) {
		model1 = model;
		String addressId = paramService.getString("address_id", "");
		String method = paramService.getString("shipping_method", "");
		String comment = paramService.getString("comment", null);
		addressId1 = addressId;
		method1 = method;
		comment1 = comment;

		Address address = addressService.getAddressById(Integer.parseInt(addressId));

		Discount discount = cartService.getDiscount();

		if (discount.getId() == 0) {
			discount = null;
		}

		int code;
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = formatter.format(date);

		while (true) {
			code = (int) Math.floor(((Math.random() * 899999) + 100000));
			List<Order> list = orderService.getOrderByName(String.valueOf(code));
			if (list.isEmpty()) {
				break;
			}
		}

		List<CartModel> listCartModel = new ArrayList<>(cartService.getItems());
		for (CartModel cart : listCartModel) {
			Order order = new Order();
			Product product = cart.getProduct();
			order.setCode(String.valueOf(code));
			order.setAddress(address);
			order.setDiscount(discount);
			order.setProduct(product);
			order.setQuality(cart.getQuality());
			order.setDate(strDate);
			order.setMethod(method);
			order.setStatus("0");
			order.setComment(comment);
			orderService.save(order);

			product.setQuality(product.getQuality() - cart.getQuality());
			productService.saveProduct(product);
		}

		discountService.updateQuality(discount);

		cartService.clear();
		cartService.clearDiscount();
		model.addAttribute("cart", cartService);

		mailerService.queue(address.getUser().getEmail(), "Đặt Hàng Thành Công Tại Web DatHau Smart Farm",
				"Kính chào " + address.getUser().getFullname() + ",<br>"
						+ "Cảm ơn bạn đã mua hàng tại DatHau Smart Farm. Mã đơn hàng của bạn là " + code + "<br>"
						+ "Xin vui lòng click vào đường link http://localhost:8086/account/order/invoice/" + code
						+ " để xem chi tiết hóa đơn.<br>" + "<br><br>" + "Xin chân thành cảm ơn đã sử dụng dịch vụ,<br>"
						+ "DatHau Smart Farm");

		return "redirect:/shop/cart/checkout/success";
	}

	// Chuyển hướng người dùng đến trang hủy thanh toán nếu quá trình thanh toán
	// không thành công.
	@GetMapping("pay/cancel")
	public String cancelPay() {
		return "user/checkout/cancel";
	}

	// Chuyển hướng người dùng đến trang thông báo thành công sau khi thanh toán
	// hoàn tất.
	@GetMapping("/shop/cart/checkout/success")
	public String success(Model model) {
		return "user/checkout/success";
	}

	// Tính tổng chi phí của các sản phẩm trong giỏ hàng.
	@ModelAttribute("total")
	public int total() {
		List<CartModel> list = new ArrayList<>(cartService.getItems());
		int total = 0;
		for (CartModel i : list) {
			total = total + i.getProduct().getPrice() * i.getQuality();
		}
		return total;
	}

	// Lấy danh sách địa chỉ của người dùng hiện đang đăng nhập.
	@ModelAttribute("listAddress")
	public List<Address> getListAddress(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		return addressService.findListAddressByEmail(username);
	}

}
