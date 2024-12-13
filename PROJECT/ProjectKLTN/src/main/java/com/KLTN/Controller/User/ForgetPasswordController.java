package com.KLTN.Controller.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.KLTN.Entity.User;
import com.KLTN.Model.UserRegister;
import com.KLTN.Service.UserService;
import com.KLTN.Service.Impl.MailerServiceImpl;

@Controller
public class ForgetPasswordController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder pe;

	@Autowired
	private MailerServiceImpl mailerService;

	// Phương thức này hiển thị form để người dùng nhập email nhằm lấy lại mật khẩu.
	// Tạo một UserRegister trống và gắn vào model dưới tên userForm để hiển thị trên trang.
	// Trả về trang "user/security/forget-password".
	@GetMapping("/forget-password")
	public String displayFormForgetPassword(Model model) {
		UserRegister userForm = new UserRegister();
		model.addAttribute("userForm", userForm);
		return "user/security/forget-password";
	}

	// xử lý logic sau khi người dùng gửi form "quên mật khẩu".
	@PostMapping("/forget-password")
	public String handlerFormForgetPassword(Model model, @ModelAttribute("userForm") @Validated UserRegister userForm,
			BindingResult result) {
		if (userForm.getEmail().isEmpty()) {
			result.rejectValue("email", "NotBlank.userRegister.email");
		} else {
			User user = userService.findUserByEmail(userForm.getEmail());
			if (user == null) {
				result.rejectValue("email", "NotExist.userLogin.username");
			} else {
				String password = pe.encode(user.getPassword());
				mailerService.queue(userForm.getEmail(), "Làm mới mật khẩu!",
						"Vui lòng click vào link này: " + "http://localhost:8086/reset-password?code=" + password
								+ "&email=" + user.getEmail() + " để reset mật khẩu.");
			}
		}

		if (result.hasErrors()) {
			return "user/security/forget-password";
		}

		model.addAttribute("alert", "Thông báo!");
		model.addAttribute("message", "Vui lòng kiểm tra email để thay đổi mật khẩu!");
		return "user/security/success";
	}
}
