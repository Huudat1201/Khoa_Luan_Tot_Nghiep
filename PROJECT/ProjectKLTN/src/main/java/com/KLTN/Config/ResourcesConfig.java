package com.KLTN.Config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/*
 * Cấu hình nguồn tài nguyên thông báo cho ứng dụng Spring Boot,
 * đảm bảo rằng các thông báo sẽ được lấy từ file validator.properties trong thư mục messages và sử dụng mã hóa UTF-8.
 */
@Configuration
public class ResourcesConfig {

	@Bean("messageSource")
	MessageSource getMessageSource() {
		ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
		ms.setBasename("classpath:messages/validator");
		ms.setDefaultEncoding("UTF-8");
		return ms;
	}

}
