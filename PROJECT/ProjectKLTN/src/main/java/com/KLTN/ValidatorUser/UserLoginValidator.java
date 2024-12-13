package com.KLTN.ValidatorUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.KLTN.Entity.User;
import com.KLTN.Model.UserLogin;
import com.KLTN.Service.UserService;

/*
 *  xác thực thông tin đăng nhập của người dùng. 
 */
@Component
public class UserLoginValidator implements Validator {
	
	@Autowired	
	private UserService userService;

	// Kiểm tra xem UserLoginValidator có thể dùng để validate các instance của lớp được chỉ định hay không
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == UserLogin.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// Liên kết Object được truyền vào với lớp UserLogin.
		UserLogin userLogin = (UserLogin) target;
		
		// Bắt lỗi nếu người dùng không nhập username.
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotBlank.userLogin.username");
		
		// Bắt lỗi nếu người dùng không nhập mật khẩu.
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotBlank.userLogin.password");

		// Xử lý các trường hợp người dùng không nhập đúng thông tin như trong database.
		if (!errors.hasFieldErrors()) { // Kiểm tra nếu không có lỗi cho các field.
			// Tìm user bằng username.
			User user = userService.findUserByEmail(userLogin.getUsername());
			
			// Xử lý trường hợp không tìm thấy user nào.
			if (user == null) {
				// Thông báo sai tên đăng nhập.
				errors.rejectValue("username", "NotExist.userLogin.username");
				errors.rejectValue("password", "NotExist.userLogin.password");
			} else {
				// Nếu người dùng đăng nhập đúng nhưng sai mật khẩu -> Tiến hành thông báo sai mật khẩu.
				if (userLogin.getPassword().equals(user.getPassword()) == false) {
					errors.rejectValue("password", "NotExist.userLogin.password");
				}
			}
		}
	}

}
