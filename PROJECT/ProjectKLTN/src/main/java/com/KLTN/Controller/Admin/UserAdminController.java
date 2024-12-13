package com.KLTN.Controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserAdminController {

	@GetMapping("/admin/user/form")
	public String form(Model model) {
		model.addAttribute("enableBtnUpdate", false);
		return "admin/user/form";
	}

	@GetMapping("/admin/user/list")
	public String list(Model model) {
		return "admin/user/list";
	}

	@GetMapping("/admin/user/update/{id}")
	public String update(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("userId", id);
		model.addAttribute("enableBtnUpdate", true);
		return "admin/user/form";
	}
	
}
