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
public class UserLogin {
	
	// Thông tin tên người dùng (User name)
    String username;

    // Thông tin mật khẩu (Password)
    String password;
    
}
