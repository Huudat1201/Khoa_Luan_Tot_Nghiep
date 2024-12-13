package com.KLTN.Controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BlogAdminController {

	@GetMapping("/admin/blogs/form")
	public String form(Model model) {
		model.addAttribute("enableBtnUpdate", false);
		return "admin/blog/blog_form";
	}

	@GetMapping("/admin/blogs/list")
	public String list(Model model) {
		return "admin/blog/blog_list";
	}

	@GetMapping("/admin/blogs/update/{id}")
	public String update(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("blogId", id);
		model.addAttribute("enableBtnUpdate", true);
		return "admin/blog/blog_form";
	}
}
