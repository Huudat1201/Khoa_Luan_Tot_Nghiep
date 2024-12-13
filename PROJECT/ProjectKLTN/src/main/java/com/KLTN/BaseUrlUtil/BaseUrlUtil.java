package com.KLTN.BaseUrlUtil;

import javax.servlet.http.HttpServletRequest;

/*
 * Class BaseUrlUtil này tập trung vào việc tạo URL cơ sở (base URL) cho ứng dụng.
 * Bản chất của nó là trừu tượng hóa và tiêu chuẩn hóa quá trình lấy URL cơ sở,
 * giúp các phần khác trong ứng dụng có thể dễ dàng truy xuất URL này một cách thống nhất mà không phải lặp lại
 * logic xử lý URL.
 */
public class BaseUrlUtil {

	public static String getBaseURL(HttpServletRequest request) {
		// Lấy giao thức (http hoặc https) từ yêu cầu.
		String scheme = request.getScheme();

		// Lấy tên server (domain hoặc IP) từ yêu cầu.
		String serverName = request.getServerName();
		
		// Lấy số cổng từ yêu cầu (ví dụ: 80 cho http, 443 cho https).
		int serverPort = request.getServerPort();
		
		// Lấy context path của ứng dụng (đường dẫn cơ sở).
		String contextPath = request.getContextPath();
		
		// Tạo một đối tượng StringBuffer để xây dựng URL.
		StringBuffer url = new StringBuffer();
		
		// Thêm giao thức và tên server vào URL.
		url.append(scheme).append("://").append(serverName);
		
		// Nếu cổng không phải là 80 (http) hoặc 443 (https), thì thêm cổng vào URL.
		if ((serverPort != 80) && (serverPort != 443)) {
			url.append(":").append(serverPort);
		}
		
		// Thêm context path vào URL.
		url.append(contextPath);
		
		// Nếu URL kết thúc bằng dấu "/", thêm một dấu "/" để đảm bảo đúng định dạng.
		if (url.toString().endsWith("/")) {
			url.append("/");
		}
		
		// Trả về chuỗi URL hoàn chỉnh.
		return url.toString();
	}

}
