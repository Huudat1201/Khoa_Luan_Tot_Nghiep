package com.KLTN.Service;

import javax.mail.MessagingException;

import com.KLTN.Model.MailInfo;

public interface MailerService {
	// Gửi ngay lập tức một email, sử dụng đối tượng MailInfo. Có thể ném ra
	// MessagingException nếu có lỗi xảy ra.
	void send(MailInfo mail) throws MessagingException;

	// Gửi ngay lập tức một email với thông tin về người nhận, chủ đề, và nội dung
	// email.
	// Có thể ném ra MessagingException.
	void send(String to, String subject, String body) throws MessagingException;

	// Đưa một email vào hàng đợi gửi, sử dụng đối tượng MailInfo.
	void queue(MailInfo mail);

	// Đưa một email vào hàng đợi gửi với thông tin về người nhận, chủ đề, và nội
	// dung email.
	void queue(String to, String subject, String body);
}
