package com.KLTN.Controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactAdminController {

	@GetMapping("/admin/contact/list/pending")
	public String listPending(Model model) {
		return "admin/contact/list_pending";
	}

	@GetMapping("/admin/contact/list/approved")
	public String listApproved(Model model) {
		return "admin/contact/list_approved";
	}

}
