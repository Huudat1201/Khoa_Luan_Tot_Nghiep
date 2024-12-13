package com.KLTN.Controller.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InfomationController {

	@GetMapping("/policy")
	public String Policy() {
		return "user/information/policy";
	}

	@GetMapping("/termCondition")
	public String TermCondition() {
		return "user/information/term_condition";
	}

	@GetMapping("/aboutUs")
	public String AboutUS() {
		return "user/information/about_us";
	}

	@GetMapping("/delivery")
	public String Delivery() {
		return "user/information/delivery";
	}

}
