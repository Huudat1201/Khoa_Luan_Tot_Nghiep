package com.KLTN.Controller.Admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.KLTN.Model.StatisticalRevenue;
import com.KLTN.Service.OrderService;
import com.KLTN.Service.ProductService;
import com.KLTN.Service.UserService;

@Controller
public class IndexAdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private ProductService productService;

	@GetMapping("/admin/index")
	public String index(Model model) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String[] today = formatter.format(date).toString().split("-");

		long order = orderService
				.getStatisticalTotalOrderOnMonth(Integer.parseInt(today[1]), Integer.parseInt(today[0]))
				.getOrderSuccess();
		double revenue = 0;
		List<StatisticalRevenue> listRevenue = orderService.listStatisticalRevenue(Integer.parseInt(today[1]),
				Integer.parseInt(today[0]));

		for (StatisticalRevenue item : listRevenue) {
			revenue = revenue + item.getPrice();
		}
		
		// Làm tròn xuống đến 3 chữ số thập phân
	    revenue = Math.floor(revenue * 1000) / 1000.0;

		long customer = userService.findAllUserNotRoleAdmin().size();

		long product = productService.findAll().size();

		model.addAttribute("product", product);
		model.addAttribute("customer", customer);
		model.addAttribute("revenue", revenue);
		model.addAttribute("order", order);
		return "admin/dashboard/index";
	}
}
