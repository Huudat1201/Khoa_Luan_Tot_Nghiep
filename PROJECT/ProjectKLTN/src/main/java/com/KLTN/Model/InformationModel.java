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
public class InformationModel {
	
	// Họ và tên của người dùng (User's full name)
    String fullName;

    // Địa chỉ email của người dùng (User's email address)
    String email;

    // Mật khẩu của người dùng (User's password)
    String password;

    // Ngày sinh của người dùng (User's birthday)
    String birthday;

    // Giới tính của người dùng (User's gender: 1 = Nam, 0 = Nữ, v.v.)
    int gender;

    // Đăng ký nhận bản tin (Subscribe to newsletter: 1 = Có, 0 = Không)
    int news;
	
}
