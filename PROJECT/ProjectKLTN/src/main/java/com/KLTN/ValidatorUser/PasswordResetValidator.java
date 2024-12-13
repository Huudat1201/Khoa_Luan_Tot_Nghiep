package com.KLTN.ValidatorUser;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.KLTN.Model.ResetPassword;

/*
 * Xác thực thông tin khi người dùng muốn đặt lại mật khẩu.
 */
@Component
public class PasswordResetValidator implements Validator {

	// Kiểm tra xem PasswordResetValidator có thể validate các instance của lớp được chỉ
	// định hay không.
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == ResetPassword.class;
	}

	/*
	 * Phương thức chính để thực hiện validate đối tượng được truyền vào.
	 */
	@Override
	public void validate(Object target, Errors errors) {
		// Liên kết Object được truyền vào với lớp ResetPassword.
		ResetPassword userRegister = (ResetPassword) target;

		// Bắt lỗi nếu trường password để trống hoặc chứa toàn khoảng trắng.
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotBlank.userRegister.password");
		// Bắt lỗi nếu trường confirmPassword(xác nhận mật khẩu) để trống hoặc chứa toàn
		// khoảng trắng.
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotBlank.userRegister.confirmPassword");

		if (!errors.hasFieldErrors()) { // Kiểm tra nếu không có lỗi ở các field.
			// Kiểm tra xem trường confirmPassword có trùng cái trường password không? ->
			// Nếu không thì thông báo là
			// xác nhận mật khẩu không trùng với mật khẩu.
			if (userRegister.getConfirmPassword().equals(userRegister.getPassword()) == false) {
				errors.rejectValue("confirmPassword", "NotDuplicate.userRegister.confirmPassword");
			}
		}

		// Nếu không có lỗi nào ở trường password, tiến hành kiểm tra độ dài của
		// password.
		if (!errors.hasFieldErrors("password")) {
			// Kiểm tra nếu password có ít hơn 7 ký tự -> Thông báo lỗi nếu password có độ
			// dài nhỏ hơn 7
			if (userRegister.getPassword().length() < 7) {
				errors.rejectValue("password", "Min.userRegister.password");
			}

			// Kiểm tra nếu password có nhiều hơn 15 ký tự -> Thông báo lỗi nếu password có
			// độ dài lớn hơn 15.
			if (userRegister.getPassword().length() > 15) {
				errors.rejectValue("password", "Max.userRegister.password");
			}
		}
	}

}
