package com.KLTN.ValidatorUser;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.KLTN.Entity.User;
import com.KLTN.Model.UserRegister;
import com.KLTN.Service.UserService;

/*
 *  Xác thực dữ liệu khi người dùng đăng ký tài khoản mới.
 */
@Component
public class UserRegistrationValidator implements Validator {

	@Autowired
	UserService userService;

	// Sử dụng EmailValidator để kiểm tra định dạng email.
	private EmailValidator emailValidator = EmailValidator.getInstance();

	// Kiểm tra xem ResetPassValidator có thể validate các instance của lớp được chỉ
	// định hay không.
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == UserRegister.class;
	}

	// Phương thức chính để thực hiện validate đối tượng được truyền vào.
	@Override
	public void validate(Object target, Errors errors) {
		// Liên kết Object được truyền vào với lớp UserRegister.
		UserRegister userRegister = (UserRegister) target;

		// Kiểm tra xem trường họ tên có bị để trống hoặc chỉ chứa khoảng trắng.
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fullName", "NotBlank.userRegister.fullname");
		// Kiểm tra xem trường email có bị để trống hoặc chỉ chứa khoảng trắng.
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotBlank.userRegister.email");
		// Kiểm tra xem trường mật khẩu có bị để trống hoặc chỉ chứa khoảng trắng.
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotBlank.userRegister.password");
		// Kiểm tra xem trường xác nhận mật khẩu có bị để trống hoặc chỉ chứa khoảng
		// trắng.
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotBlank.userRegister.confirmPassword");

		// Kiểm tra email có tồn tại trong hệ thống không?
		if (!errors.hasFieldErrors("email")) {
			// Kiểm tra email có đúng định dạng không?
			if (!this.emailValidator.isValid(userRegister.getEmail())) {
				errors.rejectValue("email", "NotPattern.userRegister.email");
			} else {
				// Kiểm tra xem email có trùng với email của user nào đã đăng ký trước đó không?
				// -> Nếu tài khoản != Thông báo lỗi nếu email đã tồn tại.
				User user = userService.findUserByEmail(userRegister.getEmail());
				if (user != null) {
					errors.rejectValue("email", "Duplicate.userRegister.email");
				}
			}
		}

		// Kiểm tra mật khẩu có đúng định dạng
		if (!errors.hasFieldErrors("password")) {
			// Kiểm tra mật khẩu phải có ít nhất 7 ký tự.
			if (userRegister.getPassword().length() < 7) {
				errors.rejectValue("password", "Min.userRegister.password");
			}

			// Kiểm tra mật khẩu không được vượt quá 30 ký tự.
			if (userRegister.getPassword().length() > 30) {
				errors.rejectValue("password", "Max.userRegister.password");
			}
		}

		// Kiểm tra xác nhận mật khẩu có trùng khớp với mật khẩu hay không
		if (!errors.hasFieldErrors("confirmPassword")) {
			// Nếu confirmPassword không trùng với password -> Thông báo lỗi nếu xác nhận
			// mật khẩu không trùng với mật khẩu.
			if (!userRegister.getPassword().equals(userRegister.getConfirmPassword())) {
				errors.rejectValue("confirmPassword", "NotDuplicate.userRegister.confirmPassword");
			}
		}

	}

}
