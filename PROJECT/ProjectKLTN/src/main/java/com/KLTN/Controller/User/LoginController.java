package com.KLTN.Controller.User;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.KLTN.Model.UserLogin;
import com.KLTN.Service.UserService;
import com.KLTN.ValidatorUser.UserLoginValidator;

@Controller
public class LoginController {

	@Autowired
	UserService userService;

	@Autowired
	private UserLoginValidator loginFormValidator;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		Object target = binder.getTarget();
		if (target == null) {
			return;
		}
		if (target.getClass() == UserLogin.class) {
			binder.setValidator(loginFormValidator);
		}
	}

	@GetMapping("/login")
	public String login(Model model) {
		UserLogin userLogin = new UserLogin();
		model.addAttribute("userLogin", userLogin);

		return "user/security/login";
	}

	@PostMapping("/login")
	public String handlerLoginForm(Model model, @ModelAttribute("userLogin") @Validated UserLogin userLogin,
			BindingResult result) {
		if (result.hasErrors()) {
			return "user/security/login";
		}

		return "user/home/index";
	}

	@GetMapping("/login/success")
	public String loginSuccess(Model model, HttpServletRequest request) {
		return "redirect:/index";
	}
}
