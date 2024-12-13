package com.KLTN.Config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.KLTN.Service.UserService;
import com.KLTN.Service.Impl.UserDetailsServiceImpl;

/*
 * Cấu hình Spring Security.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	UserService userService;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	DataSource dataSource;

	// Đăng ký userDetailsService để Spring Security có thể dùng dịch vụ
	// UserDetailsServiceImpl để xác thực người dùng.
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	// Định nghĩa BCryptPasswordEncoder như một bean, cho phép mã hóa mật khẩu với
	// thuật toán BCrypt.
	@Bean
	BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Bỏ qua các yêu cầu HTTP OPTIONS trong bảo mật, thường để hỗ trợ các yêu cầu
	// từ phía client-side (ví dụ: AJAX requests).
	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Tắt bảo vệ CSRF (Cross-Site Request Forgery) để đơn giản hóa các yêu cầu
		// HTTP.
		http.csrf().disable();

		// Chỉ cho phép người dùng có ROLE_ADMIN hoặc ROLE_DIRECTOR truy cập vào các URL
		// bắt đầu với /admin/**
		http.authorizeRequests().antMatchers("/admin/**").access("hasAnyRole('ROLE_ADMIN', 'ROLE_DIRECTOR')");

		// Các URL này yêu cầu quyền truy cập của ROLE_USER, ROLE_ADMIN, hoặc
		// ROLE_DIRECTOR.
		http.authorizeRequests().antMatchers("/shop/profile/**", "/shop/favorite/**", "/shop/cart/checkout", "/account",
				"/account/**", "/rest/favorite/add/**")
				.access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_DIRECTOR')");

		// Cho phép tất cả các yêu cầu khác mà không cần xác thực.
		http.authorizeRequests().anyRequest().permitAll();

		// Chuyển hướng người dùng tới trang /403page khi bị từ chối quyền truy cập.
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403page");

		// Cấu hình trang đăng nhập và các tham số, đường dẫn liên quan.
		http.authorizeRequests().and().formLogin().loginPage("/login").usernameParameter("username")
				.passwordParameter("password").failureForwardUrl("/login").defaultSuccessUrl("/login/success", false);

		// Cấu hình URL để logout và trang sẽ chuyển hướng đến sau khi đăng xuất
		// (/index).
		http.authorizeRequests().and().logout().logoutUrl("/logout").logoutSuccessUrl("/index");

		// Bật tính năng ghi nhớ đăng nhập với thời gian hợp lệ là 86400 giây (1 ngày).
		http.authorizeRequests().and().rememberMe().tokenValiditySeconds(86400);
	}

}
