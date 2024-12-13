package com.KLTN.Controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductAdminController {
	
	@GetMapping("/admin/product/form")
	public String form(Model model) {
		model.addAttribute("enableBtnUpdate", false);
		return "admin/products/form";
	}
	
	@GetMapping("/admin/product/list")
	public String list(Model model) {
		return "admin/products/list";
	}
		
	@GetMapping("/admin/product/update/{id}")
	public String update(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("productId", id);
		model.addAttribute("enableBtnUpdate", true);
		return "admin/products/form";
	}
	
}
