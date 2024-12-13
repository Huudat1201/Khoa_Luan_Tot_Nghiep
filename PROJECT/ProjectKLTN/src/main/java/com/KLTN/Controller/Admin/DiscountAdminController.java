package com.KLTN.Controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DiscountAdminController {

	@GetMapping("/admin/discount/form")
	public String form(Model model) {
		model.addAttribute("enableBtnUpdate", false);
		return "admin/discount/form";
	}

	@GetMapping("/admin/discount/list")
	public String list(Model model) {
		return "admin/discount/list";
	}

	@GetMapping("/admin/discount/update/{id}")
	public String update(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("discountId", id);
		model.addAttribute("enableBtnUpdate", true);
		return "admin/discount/form";
	}

	@GetMapping("/admin/discount/user/list")
	public String userList(Model model) {
		return "admin/discount/listUserDiscount";
	}

}
