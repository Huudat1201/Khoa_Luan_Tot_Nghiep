package com.KLTN.Controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderAdminController {

	@GetMapping("/admin/order/list/pending")
	public String pending(Model model) {
		return "admin/order/order_pending";
	}

	@GetMapping("/admin/order/list/shipping")
	public String shipping(Model model) {
		return "admin/order/order_shipping";
	}

	@GetMapping("/admin/order/list/success")
	public String success(Model model) {
		return "admin/order/order_success";
	}

	@GetMapping("/admin/order/list/cancel")
	public String cancel(Model model) {
		return "admin/order/order_cancel";
	}

}
