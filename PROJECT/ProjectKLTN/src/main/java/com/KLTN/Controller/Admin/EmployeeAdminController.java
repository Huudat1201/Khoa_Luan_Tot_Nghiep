package com.KLTN.Controller.Admin;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class EmployeeAdminController {

	@GetMapping("/admin/employees/list")
	public String list(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		model.addAttribute("username", username);
		return "admin/employees/list";
	}

	@GetMapping("/admin/employees/form")
	public String form(Model model) {
		model.addAttribute("enableBtnUpdate", false);
		return "admin/employees/form";
	}

	@GetMapping("/admin/employees/update/{id}")
	public String update(Model model, @PathVariable("id") Integer id) {
		System.out.println(id);
		model.addAttribute("userId", id);
		model.addAttribute("enableBtnUpdate", true);
		return "admin/employees/form";
	}
}
