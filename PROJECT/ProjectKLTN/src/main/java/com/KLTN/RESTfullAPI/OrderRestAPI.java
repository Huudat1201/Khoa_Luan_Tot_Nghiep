package com.KLTN.RESTfullAPI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.KLTN.Model.DetailOrder;
import com.KLTN.Model.OrderModel;
import com.KLTN.Service.OrderService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/order")
public class OrderRestAPI {
	
	@Autowired
	private OrderService orderService;

	// Lấy danh sách đơn hàng đang chờ xác nhận.
	@GetMapping("/pending")
	public List<OrderModel> getListOrder() {
		return orderService.listOrderGroupByCode();
	}
	
	// Lấy danh sách đơn hàng đang vận chuyển.
	@GetMapping("/shipping")
	public List<OrderModel> getListOrderShipping() {
		return orderService.listOrderGroupByCodeShipping();
	}
	
	// Lấy danh sách đơn hàng đã thành công.
	@GetMapping("/success")
	public List<OrderModel> getListOrderSuccess() {
		return orderService.listOrderGroupByCodeSuccess();
	}
	
	// Lấy danh sách đơn hàng đã bị hủy.
	@GetMapping("/cancel")
	public List<OrderModel> getListOrderCancel() {
		return orderService.listOrderGroupByCodeCancel();
	}
	

	// Lấy đơn hàng kiểm đang chờ kiểm duyệt theo cái id.
	@GetMapping("/pending/{id}")
	public DetailOrder listOrderByCodeAndUsername(@PathVariable("id") String id) {
		return orderService.getDetailOrderByCode(id);
	}
	
	// Cập nhật đơn hàng đã đồng ý theo cái id.
	@PutMapping("/approve/{id}")
	public void approve(@PathVariable("id") String id) {
		orderService.approveOrder(id);
	}
	
	// Cập nhật đơn hàng đã bị hủy theo id.
	@PutMapping("/cancel/{id}")
	public void cancel(@PathVariable("id") String id) {
		orderService.cancelOrder(id);
	}
	
	// Cập nhật đơn hàng đang vận chuyển theo cái id.
	@PutMapping("/shipped/{id}")
	public void shipped(@PathVariable("id") String id) {
		orderService.shippedOrder(id);
	}
	
	// Xóa một đơn hàng.
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable("id") String id) {
		orderService.deleteOrder(id);
	}
	
}
