package com.KLTN.SharedResourceInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.KLTN.Service.CategoryService;
import com.KLTN.Service.InformationShopService;
import com.KLTN.Service.SessionService;
import com.KLTN.Service.ShoppingCartService;

/*
 * Tự động hóa việc chia sẻ dữ liệu chung (danh mục sản phẩm, thông tin cửa hàng, giỏ hàng) 
 * cho tất cả các request mà không cần phải lặp lại logic này trong từng controller.
 * -> mục tiêu là đơn giản hóa quản lý dữ liệu chung và giữ tính nhất quán cho toàn bộ ứng dụng.
 */
@Component
public class SharedResourceInterceptor implements HandlerInterceptor {
	
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private InformationShopService informationService;

	@Autowired
	private SessionService sessionService;

	@Autowired
	private ShoppingCartService cartService;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		// Thêm danh sách các danh mục sản phẩm vào request để truyền sang view.
		request.setAttribute("categories", categoryService.findAll());
		
		// Thêm thông tin cửa hàng vào request để truyền sang view.
		request.setAttribute("information", informationService.getOneInformationShop());
		
		// Lưu giỏ hàng vào session với khóa là "sessionProduct".
		sessionService.set("sessionProduct", cartService);
	}
	
}
