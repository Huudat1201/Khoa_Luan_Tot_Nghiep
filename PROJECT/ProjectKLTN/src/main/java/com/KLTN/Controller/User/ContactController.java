package com.KLTN.Controller.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.KLTN.Service.ContactService;
import com.KLTN.Service.UserService;

@Controller
public class ContactController {

	@Autowired
	UserService userService;

	@Autowired
	ContactService contactService;

	// hiển thị trang liên hệ cho người dùng.
	@GetMapping("/contact")
	public String index(Model model) {
		return "user/contact/contact_form";
	}
	
}
