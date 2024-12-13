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
public class ResetPassword {
	
	// Mật khẩu mới (New Password)
    String password;
    
    // Xác nhận mật khẩu mới (Confirm New Password)
    String confirmPassword;
	
}
