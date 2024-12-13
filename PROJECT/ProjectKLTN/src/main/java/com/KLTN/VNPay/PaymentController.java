package com.KLTN.VNPay;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("${spring.application.api-prefix}/payment")
@RequiredArgsConstructor
public class PaymentController {

	private final PaymentService paymentService;

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

	// Hiển thị form nhập thông tin thanh toán
	@GetMapping("/form")
	public String showPaymentForm(Model model) {
		// Tính tổng tiền từ giỏ hàng
		int totalAmount = cartService.getItems().stream()
				.mapToInt(item -> item.getProduct().getPrice() * item.getQuality()).sum();

		// Đưa tổng tiền vào model để truyền sang view
		model.addAttribute("totalAmount", totalAmount);

		return "/user/VNPay/payment-form"; // Trả về tên của view để hiển thị
	}

	// Xử lý thanh toán
	@GetMapping("/vn-pay")
	public void pay(@RequestParam("amount") String amount, @RequestParam("bankCode") String bankCode,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// Thêm thông tin vào request để PaymentService xử lý
		request.setAttribute("amount", amount);
		request.setAttribute("bankCode", bankCode);

		// Gọi service để tạo URL thanh toán
		String paymentUrl = paymentService.createVnPayPayment(request).getPaymentUrl();

		// Chuyển hướng người dùng đến URL thanh toán
		response.sendRedirect(paymentUrl);
	}

	// Xử lý phản hồi từ VNPay
	@GetMapping("/vn-pay-callback")
	public void payCallbackHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String status = request.getParameter("vnp_ResponseCode");

		// Kiểm tra mã phản hồi từ VNPay
		if ("00".equals(status)) {
			
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username = ((UserDetails) principal).getUsername();

			// Lấy danh sách địa chỉ của người dùng dựa trên email
			List<Address> listAddress = addressService.findListAddressByEmail(username);

			// Lấy địa chỉ đầu tiên trong danh sách
			Address firstAddress = listAddress.get(0);
			
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
				order.setAddress(firstAddress);
				order.setDiscount(discount);
				order.setProduct(product);
				order.setQuality(cart.getQuality());
				order.setDate(strDate);
				order.setMethod("1");
				order.setStatus("0");
				order.setComment("Giao hàng đúng địa chỉ");
				orderService.save(order);

				product.setQuality(product.getQuality() - cart.getQuality());
				productService.saveProduct(product);
			}

			discountService.updateQuality(discount);

			cartService.clear();
			cartService.clearDiscount();
			mailerService.queue(firstAddress.getUser().getEmail(), "Đặt Hàng Thành Công Tại Web DatHau Smart Farm",
					"Kính chào " + firstAddress.getUser().getFullname() + ",<br>"
							+ "Cảm ơn bạn đã mua hàng tại DatHau Smart Farm. Mã đơn hàng của bạn là " + code + "<br>"
							+ "Xin vui lòng click vào đường link http://localhost:8086/account/order/invoice/" + code
							+ " để xem chi tiết hóa đơn.<br>" + "<br><br>"
							+ "Xin chân thành cảm ơn đã sử dụng dịch vụ,<br>" + "DatHau Smart Farm");
			response.sendRedirect("http://localhost:8086/shop/cart/checkout/success");
		} else {
			// Thanh toán thất bại, chuyển hướng tới trang lỗi
			response.sendRedirect("http://localhost:8086/index");
		}
	}

}
