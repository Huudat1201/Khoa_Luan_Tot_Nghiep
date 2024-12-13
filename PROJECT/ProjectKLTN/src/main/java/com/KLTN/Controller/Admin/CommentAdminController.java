package com.KLTN.Controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommentAdminController {

	@GetMapping("/admin/comment/list/approved")
	public String listApproved(Model model) {
		return "admin/comment/list_approved";
	}

	@GetMapping("/admin/comment/list/pending")
	public String listPending(Model model) {
		return "admin/comment/list_pending";
	}

}
