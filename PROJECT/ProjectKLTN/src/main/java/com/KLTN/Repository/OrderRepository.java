package com.KLTN.Repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.KLTN.Entity.Order;
import com.KLTN.Model.BestSellerModel;
import com.KLTN.Model.OrderModel;
import com.KLTN.Model.StatisticalOrder;
import com.KLTN.Model.StatisticalProductDay;

public interface OrderRepository extends JpaRepository<Order, Integer>{
	
	// Năm có số lượng đơn hàng đặt nhiều nhất.
	@Query("SELECT MAX(YEAR(o.date)) FROM Order o")
	int getMaxYearOrder();
	
	// Năm có số lượng đơn hàng đặt ít nhất.
	@Query("SELECT MIN(YEAR(o.date)) FROM Order o")
	int getMinYearOrder();
	
	// Truy vấn danh sách đơn hàng dựa trên mã đơn hàng và địa chỉ email người dùng
	// với mã đơn hàng (code) khớp với tham số truyền vào
	// và email của người dùng khớp với tham số username trong đối tượng Address của đơn hàng
	@Query("SELECT o FROM Order o WHERE o.code = ?1 and o.address.user.email = ?2")
	List<Order> listOrderByCodeAndUsername(String code, String username);
	
	// Truy vấn danh sách đơn hàng dựa trên mã đơn hàng (code) khớp với tham số truyền vào
	@Query("SELECT o FROM Order o WHERE o.code = ?1")
	List<Order> getOrderByName(String code);

	// Danh sách lịch sử đơn đặt hàng
	@Query("SELECT new OrderModel(o.code, o.address.Fullname, sum(o.quality), sum(o.product.price * o.quality),"
			+ " o.date, o.status, o.address.province) FROM Order o WHERE o.address.user.email = ?1"
			+ " GROUP BY o.code, o.address.Fullname, o.date, o.status, o.address.province ORDER BY o.date DESC, o.id DESC")
	List<OrderModel> listOrderHistory(String email);
	
	// Truy vấn danh sách các đơn hàng đang chờ xử lý (status = 0)
	@Query("SELECT new OrderModel(o.code, o.address.Fullname, sum(o.quality), sum(o.product.price * o.quality),"
			+ " o.date, o.status, o.address.province) FROM Order o WHERE o.status = 0"
			+ " GROUP BY o.code, o.address.Fullname, o.date, o.status, o.address.province ORDER BY o.date DESC")
	List<OrderModel> listOrderGroupByCodePending();
	
	// Truy vấn danh sách các đơn hàng đang vận chuyển (status = 1)
	@Query("SELECT new OrderModel(o.code, o.address.Fullname, sum(o.quality), sum(o.product.price * o.quality),"
			+ " o.date, o.status, o.address.province) FROM Order o WHERE o.status = 1"
			+ " GROUP BY o.code, o.address.Fullname, o.date, o.status, o.address.province ORDER BY o.date DESC")
	List<OrderModel> listOrderGroupByCodeShipping();
	
	// Truy vấn danh sách các đơn hàng giao thành công (status = 2)
	@Query("SELECT new OrderModel(o.code, o.address.Fullname, sum(o.quality), sum(o.product.price * o.quality),"
			+ " o.date, o.status, o.address.province) FROM Order o WHERE o.status = 2"
			+ " GROUP BY o.code, o.address.Fullname, o.date, o.status, o.address.province ORDER BY o.date DESC")
	List<OrderModel> listOrderGroupByCodeSuccess();
	
	// Truy vấn danh sách các đơn hàng đã hủy (status = 3)
	@Query("SELECT new OrderModel(o.code, o.address.Fullname, sum(o.quality), sum(o.product.price * o.quality),"
			+ " o.date, o.status, o.address.province) FROM Order o WHERE o.status = 3"
			+ " GROUP BY o.code, o.address.Fullname, o.date, o.status, o.address.province ORDER BY o.date DESC")
	List<OrderModel> listOrderGroupByCodeCancel();
	
	// Truy vấn danh sách các đơn hàng đã hoàn thành (status = 2) trong một ngày cụ thể
	@Query("SELECT new OrderModel(o.code, o.address.Fullname, sum(o.quality), sum(o.product.price * o.quality),"
			+ " o.date, o.status, o.address.province) FROM Order o"
			+ " WHERE (o.status = 2) AND DAY(o.date) = ?1 AND MONTH(o.date) = ?2 AND YEAR(o.date) = ?3"
			+ " GROUP BY o.code, o.address.Fullname, o.date, o.status, o.address.province ORDER BY o.date DESC")
	List<OrderModel> listStatisticalRevenueDay(int day, int month, int year);
	
	// Truy vấn danh sách các đơn hàng đã hoàn thành (status = 2) trong một tháng cụ thể
	@Query("SELECT new OrderModel(o.code, o.address.Fullname, sum(o.quality), sum(o.product.price * o.quality),"
			+ " o.date, o.status, o.address.province) FROM Order o"
			+ " WHERE (o.status = 2) AND MONTH(o.date) = ?1 AND YEAR(o.date) = ?2"
			+ " GROUP BY o.code, o.address.Fullname, o.date, o.status, o.address.province ORDER BY o.date DESC")
	List<OrderModel> listStatisticalRevenueMonth(int month, int year);
	
	// Truy vấn danh sách các đơn hàng đã hoàn thành (status = 2) trong một năm cụ thể
	@Query("SELECT new OrderModel(o.code, o.address.Fullname, sum(o.quality), sum(o.product.price * o.quality),"
			+ " o.date, o.status, o.address.province) FROM Order o WHERE (o.status = 2) AND YEAR(o.date) = ?1"
			+ " GROUP BY o.code, o.address.Fullname, o.date, o.status, o.address.province ORDER BY o.date DESC")
	List<OrderModel> listStatisticalRevenueYear(int year);
	
	// Truy vấn số lượng đơn hàng thành công (status = 2) trong một ngày cụ thể
	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 2) AND DAY(o.date) = ?1"
			+ " AND MONTH(o.date) = ?2 AND YEAR(o.date) = ?3 GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderSuccessOnDay(int day, int month, int year);
	
	// Truy vấn số lượng đơn hàng đang được vận chuyển (status = 1) trong một ngày cụ thể
	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 1) AND DAY(o.date) = ?1"
			+ " AND MONTH(o.date) = ?2 AND YEAR(o.date) = ?3 GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderTransportOnDay(int day, int month, int year);
	
	// Truy vấn số lượng đơn hàng đang chờ xử lý (status = 0) trong một ngày cụ thể
	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 0) AND DAY(o.date) = ?1"
			+ " AND MONTH(o.date) = ?2 AND YEAR(o.date) = ?3 GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderWaitOnDay(int day, int month, int year);
	
	// Truy vấn số lượng đơn hàng đã bị hủy (status = 3) trong một ngày cụ thể
	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 3) AND DAY(o.date) = ?1"
			+ " AND MONTH(o.date) = ?2 AND YEAR(o.date) = ?3 GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderCancelOnDay(int day, int month, int year);
	
	// Truy vấn số lượng đơn hàng thành công (status = 2) trong một tháng cụ thể
	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 2) AND MONTH(o.date) = ?1"
			+ " AND YEAR(o.date) = ?2 GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderSuccessOnMonth(int month, int year);
	
	// Truy vấn số lượng đơn hàng đang trong quá trình vận chuyển (status = 1) trong một tháng cụ thể.
	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 1) AND MONTH(o.date) = ?1"
			+ " AND YEAR(o.date) = ?2 GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderTransportOnMonth(int month, int year);
	
	// Truy vấn số lượng đơn hàng đang trong quá trình xử lý (status = 0) trong một tháng cụ thể.
	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 0) AND MONTH(o.date) = ?1"
			+ " AND YEAR(o.date) = ?2 GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderWaitOnMonth(int month, int year);
	
	// Truy vấn số lượng đơn hàng đã hủy (status = 3) trong một tháng cụ thể.
	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 3) AND MONTH(o.date) = ?1"
			+ " AND YEAR(o.date) = ?2 GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderCancelOnMonth(int month, int year);
	
	// Truy vấn số lượng đơn hàng đã giao thành công trong năm có status = 2.
	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 2) AND YEAR(o.date) = ?1"
			+ " GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderSuccessOnYear(int year);
	
	// Truy vấn số lượng đơn hàng đang vận chuyển trong một năm có status = 1.
	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 1) AND YEAR(o.date) = ?1"
			+ " GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderTransportOnYear(int year);
	
	// Truy vấn số lượng đơn hàng đang chờ xử lý trong một năm có status = 0.
	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 0) AND YEAR(o.date) = ?1"
			+ " GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderWaitOnYear(int year);
	
	// Truy vấn số lượng đơn hàng đã hủy trong một năm có status = 3.
	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 3) AND YEAR(o.date) = ?1"
			+ " GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderCancelOnYear(int year);
	
	// Truy vấn số liệu thống kê sản phẩm trong ngày hiện tại.
	@Query("SELECT new StatisticalProductDay(o.product.code, o.product.name, o.product.price,"
			+ " o.product.quality, sum(o.quality)) FROM Order o WHERE CAST(GETDATE() as date) = o.date"
			+ " GROUP BY o.product.code, o.product.name, o.product.price, o.product.quality")
	List<StatisticalProductDay> listStatisticalProductDay();
	
	// Truy vấn danh sách sản phẩm bán chạy.
	@Query("SELECT new BestSellerModel(o.product, sum(o.quality)) FROM Order o WHERE o.product.Deleteday = null"
			+ " AND o.product.active = 1 GROUP BY o.product ORDER BY sum(o.quality) DESC")
	List<BestSellerModel> getListBestSellerProduct(Pageable pageable);
	
}
