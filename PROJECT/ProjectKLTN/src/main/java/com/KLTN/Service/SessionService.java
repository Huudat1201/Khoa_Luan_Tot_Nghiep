package com.KLTN.Service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
	
	@Autowired
	HttpSession session;

	// Đặt một giá trị (value) vào session với tên thuộc tính là name.
	public void set(String name, Object value) {
		session.setAttribute(name, value);
	}

	// Lấy giá trị từ session dựa trên tên thuộc tính (name).
	// Phương thức này trả về giá trị được ép kiểu về kiểu dữ liệu đã xác định.
	@SuppressWarnings("unchecked")
	public <T> T get(String name) {
		return (T) session.getAttribute(name);
	}

	// Xóa thuộc tính với tên name ra khỏi session.
	public void remove(String name) {
		session.removeAttribute(name);
	}
	
}