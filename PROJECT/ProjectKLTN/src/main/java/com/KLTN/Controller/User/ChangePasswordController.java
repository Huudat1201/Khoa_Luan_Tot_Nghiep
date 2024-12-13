package com.KLTN.Controller.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChangePasswordController {

	// điều hướng người dùng đến trang thay đổi mật khẩu, và không xử lý logic thay đổi mật khẩu.
	@GetMapping("/account/change-password")
	public String index() {
		return "user/account/account_change_password";
	}

}
