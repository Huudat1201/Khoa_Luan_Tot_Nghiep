package com.KLTN.Controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatisticalAdminController {

	@GetMapping("/admin/statistical/product/day")
	public String product(Model model) {
		return "admin/statisticalProduct/top_product";
	}

	@GetMapping("/admin/statistical/product/warehouse")
	public String warehouse(Model model) {
		return "admin/statisticalProduct/warehouse_product";
	}

	@GetMapping("/admin/statistical/revenue")
	public String revenue(Model model) {
		return "admin/statisticalRevenue/list";
	}

	@GetMapping("/admin/statistical/order")
	public String order(Model model) {
		return "admin/statisticalOrder/list";
	}

}
