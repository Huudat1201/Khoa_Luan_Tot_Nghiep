package com.KLTN.Service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.KLTN.Entity.Order;
import com.KLTN.Entity.Product;
import com.KLTN.Model.BestSellerModel;
import com.KLTN.Model.DetailOrder;
import com.KLTN.Model.OrderModel;
import com.KLTN.Model.StatisticalProductDay;
import com.KLTN.Model.StatisticalRevenue;
import com.KLTN.Model.StatisticalTotalOrder;

public interface OrderService {
	
	// Danh sách các đơn hàng dựa trên mã đơn hàng (code).
	List<Order> getOrderByName(String code);
	
	// Danh sách lịch sử các đơn hàng.
	List<OrderModel> listOrderHistory();

	// Danh sách đơn hàng dựa trên mã và tên người dùng.
	List<Order> listOrderByCodeAndUsername(String id);
	
	// Danh sách các đơn hàng, được nhóm theo mã.
	List<OrderModel> listOrderGroupByCode();

	// Danh sách đơn hàng đang trong quá trình vận chuyển.
	List<OrderModel> listOrderGroupByCodeShipping();

	// Danh sách đơn hàng đã giao thành công.
	List<OrderModel> listOrderGroupByCodeSuccess();

	// Danh sách đơn hàng đã bị hủy.
	List<OrderModel> listOrderGroupByCodeCancel();

	// Thống kê sản phẩm bán hàng theo ngày.
	List<StatisticalProductDay> listStatisticalProductDay();

	// Thống kê doanh thu theo tháng và năm.
	List<StatisticalRevenue> listStatisticalRevenue(int month, int year);

	// Thống kê doanh thu theo tháng trong năm.
	List<StatisticalRevenue> listStatisticalRevenueByMonth(int year);

	// Thống kê doanh thu theo năm.
	List<StatisticalRevenue> listStatisticalRevenueByYear(int year);

	// Danh sách các năm có đơn hàng.
	List<Integer> getListYearOrder();

	// Danh sách các sản phẩm bán chạy nhất, giới hạn bởi thông tin phân trang.
	List<BestSellerModel> getListBestSellerProduct(Pageable topFour);

	// Danh sách thống kê sản phẩm trong kho.
	List<Product> listStatisticalProductWarehouse();

	// Chi tiết đơn hàng dựa trên mã đơn hàng.
	DetailOrder getDetailOrderByCode(String id);
	
	// Trả về tổng đơn hàng theo ngày, tháng, và năm.
	StatisticalTotalOrder getStatisticalTotalOrderOnDay(int day, int month, int year);

	// Trả về tổng đơn hàng theo tháng và năm.
	StatisticalTotalOrder getStatisticalTotalOrderOnMonth(int month, int year);
	
	// Trả về tổng đơn hàng theo năm.
	StatisticalTotalOrder getStatisticalTotalOrderOnYear(int year);

	// Tổng đơn hàng theo ngày, tháng, và năm cụ thể.
	StatisticalTotalOrder getStatisticalTotalOrderOnOption(int day, int month, int year);
	
	// Phê duyệt đơn hàng theo mã đơn hàng.
	void approveOrder(String id);
	
	// Hủy đơn hàng theo mã đơn hàng.
	void cancelOrder(String id);

	// Lưu đơn hàng vào cơ sở dữ liệu.
	void save(Order order);

	// Đánh dấu đơn hàng đã được giao.
	void shippedOrder(String id);

	// Xóa đơn hàng khỏi cơ sở dữ liệu.
	void deleteOrder(String id);

}
