package com.KLTN.RESTfullAPI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.KLTN.Entity.Product;
import com.KLTN.Model.StatisticalProductDay;
import com.KLTN.Model.StatisticalRevenue;
import com.KLTN.Model.StatisticalTotalOrder;
import com.KLTN.Service.OrderService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest")
public class StatisticalRestAPI {
	
	@Autowired
	private OrderService orderService;
	
	// Danh sách các năm để thống kê.
	@GetMapping("/statisticalOrder/year")
	public List<Integer> listYear(){
		return orderService.getListYearOrder();
	}
	
	// Danh sách sản phẩm tồn trong kho.
	@GetMapping("/statistical/product/warehouse")
	public List<Product> warehouse(){
		return orderService.listStatisticalProductWarehouse();
	}
	
	// Danh sách sản phẩm bán theo ngày.
	@GetMapping("/statistical/product/day")
	public List<StatisticalProductDay> productDay(){
		return orderService.listStatisticalProductDay();
	}
	
	// Thống kê doanh thu theo tháng trong năm.
	@GetMapping("/statistical/revenue/day/{month}/{year}")
	public List<StatisticalRevenue> listRevenueByDay(@PathVariable("month") int month, @PathVariable("year") int year){
		return orderService.listStatisticalRevenue(month, year);
	}
	
	// Thống kê doanh thu theo năm.
	@GetMapping("/statistical/revenue/month/{year}")
	public List<StatisticalRevenue> listRevenueByMonth(@PathVariable("year") int year){
		return orderService.listStatisticalRevenueByMonth(year);
	}
	
	// Thống kê doanh thu theo năm các năm.
	@GetMapping("/statistical/revenue/year/{year}")
	public List<StatisticalRevenue> listRevenueByYear(@PathVariable("year") int year){
		return orderService.listStatisticalRevenueByYear(year);
	}
	
	// Thống kê tổng đơn hàng theo ngày, tháng, và năm.
	@GetMapping("/statistical/order/day/{day}/{month}/{year}")
	public StatisticalTotalOrder listOrderByDay(@PathVariable("day") int day, @PathVariable("month") int month, @PathVariable("year") int year){
		return orderService.getStatisticalTotalOrderOnDay(day, month, year);
	}
	
	// Thống kê tổng đơn hàng theo tháng/năm.
	@GetMapping("/statistical/order/month/{month}/{year}")
	public StatisticalTotalOrder listOrderByMonth(@PathVariable("month") int month, @PathVariable("year") int year){
		return orderService.getStatisticalTotalOrderOnMonth(month, year);
	}
	
	// Trả về tổng đơn hàng theo năm.
	@GetMapping("/statistical/order/year/{year}")
	public StatisticalTotalOrder listOrderByYear(@PathVariable("year") int year){
		return orderService.getStatisticalTotalOrderOnYear(year);
	}
	
	// Tổng đơn hàng theo ngày, tháng, và năm cụ thể.
	@GetMapping("/statistical/order/option/{day}/{month}/{year}")
	public StatisticalTotalOrder listOrderByOption(@PathVariable("day") int day, @PathVariable("month") int month, @PathVariable("year") int year){
		return orderService.getStatisticalTotalOrderOnOption(day, month, year);
	}
	
}
