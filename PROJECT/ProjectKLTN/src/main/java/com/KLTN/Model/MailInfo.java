package com.KLTN.Model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC)
public class MailInfo {
	
	// Địa chỉ email người gửi (Sender's email address)
    String from;
    
    // Địa chỉ email người nhận (Recipient's email address)
    String to;
    
    // Danh sách địa chỉ email nhận bản sao (Carbon copy recipients)
    String[] cc;
    
    // Danh sách địa chỉ email nhận bản sao ẩn (Blind carbon copy recipients)
    String[] bcc;
    
    // Chủ đề của email (Email subject)
    String subject;
    
    // Nội dung của email (Email body content)
    String body;
    
    // Danh sách tệp đính kèm (Attachments)
    String[] attachment;

    // Constructor để khởi tạo thông tin email
    public MailInfo(String to, String subject, String body) {
        this.from = "Chuỗi cửa hàng thực phẩm sạch DATHAU SMART FARM<Huudat1201@gmail.com>"; // Địa chỉ email mặc định người gửi
        this.to = to; // Địa chỉ email người nhận
        this.subject = subject; // Chủ đề email
        this.body = body; // Nội dung email
    }

	
}
