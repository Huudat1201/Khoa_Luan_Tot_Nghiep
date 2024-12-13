package com.KLTN.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.KLTN.SharedResourceInterceptor.SharedResourceInterceptor;

/*
 * Cấu hình một interceptor để xử lý tất cả các yêu cầu đến ứng dụng của bạn,
 * ngoại trừ những đường dẫn cụ thể được loại trừ (/rest/**, /admin/**, /assets/**).
 * Interceptor này có thể được sử dụng để thực hiện các tác vụ như kiểm tra quyền truy cập, ghi log,
 * hoặc bất kỳ xử lý nào khác trước khi yêu cầu được chuyển đến controller tương ứng.
 * */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	
	@Autowired
	private SharedResourceInterceptor globalInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(globalInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/rest/**", "/admin/**", "/assets/**");
	}

}
