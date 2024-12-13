package com.KLTN.Controller.User;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.KLTN.Entity.Role;
import com.KLTN.Entity.User;
import com.KLTN.Entity.UserRole;
import com.KLTN.Model.UserRegister;
import com.KLTN.Service.RoleService;
import com.KLTN.Service.SessionService;
import com.KLTN.Service.UserRoleService;
import com.KLTN.Service.UserService;
import com.KLTN.Service.Impl.MailerServiceImpl;
import com.KLTN.ValidatorUser.UserRegistrationValidator;

@Controller
public class RegisterController {

	@Autowired
	private UserRegistrationValidator registerFormValidator;

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private UserService userService;

	@Autowired
	private SessionService sessionService;

	@Autowired
	private MailerServiceImpl mailerService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		Object target = binder.getTarget();
		if (target == null) {
			return;
		}
		if (target.getClass() == UserRegister.class) {
			binder.setValidator(registerFormValidator);
		}
	}

	@GetMapping("/register")
	public String displayFormRegister(Model model) {
		UserRegister userRegister = new UserRegister();
		model.addAttribute("userRegister", userRegister);

		return "user/security/register";
	}

	@PostMapping("/register")
	public String handlerRegisterForm(Model model, @ModelAttribute("userRegister") @Validated UserRegister userRegister,
			BindingResult result) {
		if (result.hasErrors()) {
			return "user/security/register";
		} else {
			if (userRegister.isConfirmTerm() == false) {
				model.addAttribute("checkTerm", true);
				return "user/security/register";
			} else {
				int code = (int) Math.floor(((Math.random() * 899999) + 100000));
				userRegister.setCode(code);
				mailerService.queue(userRegister.getEmail(), "Xác nhận email!", "Code xác nhận của bạn là: " + code);
				sessionService.set("user", userRegister);
			}
		}
		return "user/security/confirm-code";
	}

	@GetMapping("/register/confirm-code")
	public String displayFormConfirmMail(Model model) {
		// Hien thi trang register
		return "redirect:/register";
	}

	@PostMapping("/register/confirm-code")
	public String handlerFormConfirmMail(Model model,
			@ModelAttribute("userRegister") @Validated UserRegister userRegisterForm, BindingResult result) {
		UserRegister userRegister = sessionService.get("user");
		if (userRegisterForm.getConfirmCode().isEmpty()) {
			result.rejectValue("confirmCode", "NotBlank.userRegister.confirmCode");
		} else {
			if (!userRegisterForm.getConfirmCode().equals(String.valueOf(userRegister.getCode()))) {
				result.rejectValue("confirmCode", "NotDuplicate.userRegister.confirmCode");
			} else {
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				User user = new User();
				user.setEmail(userRegister.getEmail());
				user.setFullname(userRegister.getFullName());
				user.setPassword(userRegister.getPassword());
				user.setCreateday(timestamp.toString());
				user.setSubscribe(userRegister.getSubscribe());
				userService.save(user);
				Role role = roleService.findRoleById(1);
				UserRole userRole = new UserRole();
				userRole.setUser(user);
				userRole.setRole(role);
				userRoleService.save(userRole);
				sessionService.remove("user");
				model.addAttribute("alert", "Đăng ký thành công!");
				model.addAttribute("message", "Chúc mừng bạn đã tạo mới một tài khoản thành công!");
				return "user/security/success";
			}
		}

		return "user/security/confirm-code";
	}
}
