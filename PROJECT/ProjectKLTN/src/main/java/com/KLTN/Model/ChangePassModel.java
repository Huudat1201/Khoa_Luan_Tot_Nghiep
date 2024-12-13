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
public class ChangePassModel {
	
	// Mật khẩu cũ của người dùng (User's old password)
    String oldPass;

    // Mật khẩu mới mà người dùng muốn thay đổi (New password the user wants to set)
    String newPass;

    // Mật khẩu mới nhập lại để xác nhận (Confirmation of the new password)
    String confirmPass;
    
}
