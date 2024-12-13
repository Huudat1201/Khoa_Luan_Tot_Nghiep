package com.KLTN.Model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRegister {
	
	 // Thông tin email (Email)
    String email;

    // Thông tin họ và tên (Full name)
    String fullName;

    // Thông tin mật khẩu (Password)
    String password;

    // Thông tin xác nhận mật khẩu (Confirm Password)
    String confirmPassword;

    // Thông tin mã xác nhận (Code)
    int code;

    // Thông tin mã xác nhận (Confirm Code)
    String confirmCode;

    // Thông tin đăng ký nhận bản tin (Subscribe)
    int subscribe;

    // Thông tin xác nhận điều khoản (Confirm Term)
    boolean confirmTerm;
	
}
