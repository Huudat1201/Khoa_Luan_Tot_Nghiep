package com.KLTN.Controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CategoryAdminController {

	@GetMapping("/admin/categories/form")
	public String form(Model model) {
		model.addAttribute("enableBtnUpdate", false);
		return "admin/categories/form";
	}

	@GetMapping("/admin/categories/list")
	public String list(Model model) {
		return "admin/categories/list";
	}

	@GetMapping("/admin/categories/update/{id}")
	public String update(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("categoryId", id);
		model.addAttribute("enableBtnUpdate", true);
		return "admin/categories/form";
	}
}
