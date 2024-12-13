package com.KLTN.Controller.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Page404Controller {

	@GetMapping("/404page")
	public String display404Page(Model model) {
		return "user/security/404page";
	}

}
