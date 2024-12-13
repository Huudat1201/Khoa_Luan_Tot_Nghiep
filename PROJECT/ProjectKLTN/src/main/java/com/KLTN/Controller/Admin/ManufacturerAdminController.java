package com.KLTN.Controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ManufacturerAdminController {

	@GetMapping("/admin/manufactures/form")
	public String form(Model model) {
		model.addAttribute("enableBtnUpdate", false);
		return "admin/manufactures/form";
	}

	@GetMapping("/admin/manufactures/list")
	public String list(Model model) {
		return "admin/manufactures/list";
	}

	@GetMapping("/admin/manufactures/update/{id}")
	public String update(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("manufacturerId", id);
		model.addAttribute("enableBtnUpdate", true);
		return "admin/manufactures/form";
	}
}
