package com.KLTN.Controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class InformationAdminShopController {

	@GetMapping("/admin/shop/form")
	public String form(Model model) {
		model.addAttribute("enableBtnUpdate", false);
		return "admin/informationShop/form";
	}

	@GetMapping("/admin/shop/list")
	public String list(Model model) {
		return "admin/informationShop/list";
	}

	@GetMapping("/admin/shop/update/{id}")
	public String update(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("shopId", id);
		model.addAttribute("enableBtnUpdate", true);
		return "admin/informationShop/form";
	}

}
