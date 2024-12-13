package com.KLTN.Service.Impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.KLTN.Entity.Discount;
import com.KLTN.Entity.Order;
import com.KLTN.Entity.Product;
import com.KLTN.Model.BestSellerModel;
import com.KLTN.Model.CartModel;
import com.KLTN.Model.DetailOrder;
import com.KLTN.Model.OrderModel;
import com.KLTN.Model.StatisticalOrder;
import com.KLTN.Model.StatisticalProductDay;
import com.KLTN.Model.StatisticalRevenue;
import com.KLTN.Model.StatisticalTotalOrder;
import com.KLTN.Repository.DiscountRepository;
import com.KLTN.Repository.OrderRepository;
import com.KLTN.Repository.ProductRepository;
import com.KLTN.Service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private DiscountRepository discountRepository;

	
	/*
	 *  Danh sách các đơn hàng dựa trên mã đơn hàng (code).
	 */
	@Override
	public List<Order> getOrderByName(String code) {
		return orderRepository.getOrderByName(code);
	}

	
	/*
	 *  Danh sách lịch sử các đơn hàng.
	 */
	@Override
	public List<OrderModel> listOrderHistory() {
		// Lấy thông tin tên đăng nhập của người dùng hiện tại trong ứng dụng Spring Security.
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		List<OrderModel> list = orderRepository.listOrderHistory(username);

		for (OrderModel order : list) {
			String[] date = order.getDate().split("-");
			String result = date[2] + "/" + date[1] + "/" + date[0];
			order.setDate(result);
		}

		return list;
	}
	

	/*
	 *  Danh sách đơn hàng dựa trên mã và tên người dùng.
	 */
	@Override
	public List<Order> listOrderByCodeAndUsername(String id) {
		// Lấy thông tin tên đăng nhập của người dùng hiện tại trong ứng dụng Spring Security.
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		List<Order> list = orderRepository.listOrderByCodeAndUsername(id, username);

		for (Order order : list) {
			String[] date = order.getDate().split("-");
			String result = date[2] + "/" + date[1] + "/" + date[0];
			order.setDate(result);
		}

		return list;
	}
	
	
	/*
	 *  Danh sách các đơn hàng, được nhóm theo mã.
	 */
	@Override
	public List<OrderModel> listOrderGroupByCode() {
		List<OrderModel> listOrder = orderRepository.listOrderGroupByCodePending();

		for (OrderModel list : listOrder) {
			Order order = orderRepository.getOrderByName(list.getId()).get(0);
			if (order != null) {
				list.setDiscount(order.getDiscount());
			}
		}
		return listOrder;
	}
	
	
	/*
	 *  Danh sách đơn hàng đang trong quá trình vận chuyển.
	 */
	@Override
	public List<OrderModel> listOrderGroupByCodeShipping() {
		List<OrderModel> listOrder = orderRepository.listOrderGroupByCodeShipping();

		for (OrderModel list : listOrder) {
			Order order = orderRepository.getOrderByName(list.getId()).get(0);
			if (order != null) {
				list.setDiscount(order.getDiscount());
			}
		}

		return listOrder;
	}

	
	/*
	 *  Danh sách đơn hàng đã giao thành công.
	 */
	@Override
	public List<OrderModel> listOrderGroupByCodeSuccess() {
		List<OrderModel> listOrder = orderRepository.listOrderGroupByCodeSuccess();

		for (OrderModel list : listOrder) {
			Order order = orderRepository.getOrderByName(list.getId()).get(0);
			if (order != null) {
				list.setDiscount(order.getDiscount());
			}
		}

		return listOrder;
	}

	
	/*
	 *  Danh sách đơn hàng đã bị hủy.
	 */
	@Override
	public List<OrderModel> listOrderGroupByCodeCancel() {
		List<OrderModel> listOrder = orderRepository.listOrderGroupByCodeCancel();

		for (OrderModel list : listOrder) {
			Order order = orderRepository.getOrderByName(list.getId()).get(0);
			if (order != null) {
				list.setDiscount(order.getDiscount());
			}
		}

		return listOrder;
	}
	
	
	/*
	 *  Thống kê sản phẩm bán hàng theo ngày.
	 */
	@Override
	public List<StatisticalProductDay> listStatisticalProductDay() {
		return orderRepository.listStatisticalProductDay();
	}

	
	/*
	 *  Thống kê doanh thu theo tháng và năm.
	 */
	@Override
	public List<StatisticalRevenue> listStatisticalRevenue(int month, int year) {
		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.MONTH, month - 1);

		int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		List<StatisticalRevenue> listRevenue = new ArrayList<StatisticalRevenue>();

		for (int i = 1; i <= maxDay; i++) {
			long sum = 0;

			List<OrderModel> listOrder = new ArrayList<OrderModel>();
			listOrder = orderRepository.listStatisticalRevenueDay(i, month, year);

			if (!listOrder.isEmpty()) {
				for (OrderModel order : listOrder) {
					Discount discount = order.getDiscount();
					sum = sum + order.getTotal();
					if (discount != null) {
						sum = sum - discount.getPrice();
					}
					String province = order.getProvince();
					int shippingFee = province.equals("Hồ Chí Minh") ? 18000 : 30000;
					sum += shippingFee;

				}

			}

			double total = (double) sum / 1000000;

			StatisticalRevenue statistical = new StatisticalRevenue();
			statistical.setPrice(total);
			statistical.setDate(i);
			listRevenue.add(statistical);
		}

		return listRevenue;
	}

	
	/*
	 *  Thống kê doanh thu theo tháng trong năm.
	 */
	@Override
	public List<StatisticalRevenue> listStatisticalRevenueByMonth(int year) {
		List<StatisticalRevenue> listRevenue = new ArrayList<StatisticalRevenue>();
		for (int i = 1; i <= 12; i++) {
			long sum = 0;
			List<OrderModel> listOrder = new ArrayList<OrderModel>();
			listOrder = orderRepository.listStatisticalRevenueMonth(i, year);

			if (!listOrder.isEmpty()) {
				for (OrderModel order : listOrder) {
					Discount discount = order.getDiscount();
					sum = sum + order.getTotal();
					if (discount != null) {
						sum = sum - discount.getPrice();
					}
					String province = order.getProvince();
					int shippingFee = province.equals("Hồ Chí Minh") ? 18000 : 30000;
					sum += shippingFee;
				}

			}

			double total = (double) sum / 1000000;

			StatisticalRevenue statistical = new StatisticalRevenue();
			statistical.setPrice(total);
			statistical.setDate(i);
			listRevenue.add(statistical);

		}

		return listRevenue;
	}
	

	/*
	 *  Thống kê doanh thu theo năm.
	 */
	@Override
	public List<StatisticalRevenue> listStatisticalRevenueByYear(int year) {
		int minYear = year - 10;
		List<StatisticalRevenue> listRevenue = new ArrayList<StatisticalRevenue>();
		for (int i = 1; i <= 10; i++) {
			long sum = 0;
			List<OrderModel> listOrder = new ArrayList<OrderModel>();
			listOrder = orderRepository.listStatisticalRevenueYear(minYear + i);

			if (!listOrder.isEmpty()) {
				for (OrderModel order : listOrder) {
					Discount discount = order.getDiscount();
					sum = sum + order.getTotal();
					if (discount != null) {
						sum = sum - discount.getPrice();
					}
					String province = order.getProvince();
					int shippingFee = province.equals("Hồ Chí Minh") ? 18000 : 30000;
					sum += shippingFee;
				}

			}

			double total = (double) sum / 1000000;

			StatisticalRevenue statistical = new StatisticalRevenue();
			statistical.setPrice(total);
			statistical.setDate(minYear + i);
			listRevenue.add(statistical);

		}

		return listRevenue;

	}
	
	
	/*
	 *  Danh sách các năm có đơn hàng.
	 */
	@Override
	public List<Integer> getListYearOrder() {
		int maxYear = orderRepository.getMaxYearOrder();
		int minYear = orderRepository.getMinYearOrder();

		List<Integer> listYear = new ArrayList<Integer>();

		for (int i = minYear; i <= maxYear; i++) {
			listYear.add(i);
		}

		return listYear;
	}
	
	
	/*
	 *  Danh sách các sản phẩm bán chạy nhất, giới hạn bởi thông tin phân trang.
	 */
	@Override
	public List<BestSellerModel> getListBestSellerProduct(Pageable topFour) {
		return orderRepository.getListBestSellerProduct(topFour);
	}

	
	/*
	 *  Danh sách thống kê sản phẩm trong kho.
	 */
	@Override
	public List<Product> listStatisticalProductWarehouse() {
		return productRepository.listStatisticalProductWarehouse();
	}
	
	
	/*
	 *  Chi tiết đơn hàng dựa trên mã đơn hàng.
	 */
	@Override
	public DetailOrder getDetailOrderByCode(String id) {
		DetailOrder detailOrder = new DetailOrder();
		List<Order> listOrder = orderRepository.getOrderByName(id);

		detailOrder.setId(listOrder.get(0).getCode());
		detailOrder.setAddress(listOrder.get(0).getAddress().getDetail());
		detailOrder.setComment(listOrder.get(0).getComment());
		detailOrder.setDate(listOrder.get(0).getDate());

		Discount discount = listOrder.get(0).getDiscount();
		if (discount != null) {
			detailOrder.setDiscount(discount.getPrice());
		} else {
			detailOrder.setDiscount(0);
		}

		detailOrder.setDistrict(listOrder.get(0).getAddress().getDistrict());
		detailOrder.setFullName(listOrder.get(0).getAddress().getUser().getFullname());
		String method = "";
		if (listOrder.get(0).getMethod().equals("1")) {
			method = "Thanh toán qua VNPay";
		}
		if (listOrder.get(0).getMethod().equals("0")) {
			method = "Thanh toán bằng tiền mặt khi nhận hàng";
		}
		detailOrder.setMethod(method);
		detailOrder.setPhone(listOrder.get(0).getAddress().getPhone());
		detailOrder.setProvince(listOrder.get(0).getAddress().getProvince());
		detailOrder.setWard(listOrder.get(0).getAddress().getWard());
		int subTotal = 0;
		int total = 0;

		List<CartModel> listCartModel = new ArrayList<CartModel>();
		for (Order list : listOrder) {
			CartModel cartModel = new CartModel();
			Product product = new Product();
			product = list.getProduct();
			cartModel.setProduct(product);
			cartModel.setQuality(list.getQuality());
			listCartModel.add(cartModel);

			subTotal = subTotal + list.getProduct().getPrice() * list.getQuality();
		}

		String province = listOrder.get(0).getAddress().getProvince();
		int shippingFee = province.equals("Hồ Chí Minh") ? 18000 : 30000;
		total = subTotal + shippingFee - detailOrder.getDiscount();

		detailOrder.setSubTotal(subTotal);
		detailOrder.setTotal(total);
		detailOrder.setListOrder(listCartModel);

		return detailOrder;
	}

	
	/*
	 *  Trả về tổng đơn hàng theo ngày, tháng, và năm.
	 */
	@Override
	public StatisticalTotalOrder getStatisticalTotalOrderOnDay(int day, int month, int year) {
		List<StatisticalOrder> orderSuccess = orderRepository.getMaxOrderSuccessOnDay(day, month, year);
		List<StatisticalOrder> orderWait = orderRepository.getMaxOrderWaitOnDay(day, month, year);
		List<StatisticalOrder> orderTransport = orderRepository.getMaxOrderTransportOnDay(day, month, year);
		List<StatisticalOrder> orderCancel = orderRepository.getMaxOrderCancelOnDay(day, month, year);

		int success = orderSuccess.size();
		int wait = orderWait.size();
		int transport = orderTransport.size();
		int cancel = orderCancel.size();

		StatisticalTotalOrder totalOrder = new StatisticalTotalOrder(success, cancel, wait, transport);

		return totalOrder;
	}

	
	/*
	 *  Trả về tổng đơn hàng theo tháng và năm.
	 */
	@Override
	public StatisticalTotalOrder getStatisticalTotalOrderOnMonth(int month, int year) {
		List<StatisticalOrder> orderSuccess = orderRepository.getMaxOrderSuccessOnMonth(month, year);
		List<StatisticalOrder> orderWait = orderRepository.getMaxOrderWaitOnMonth(month, year);
		List<StatisticalOrder> orderTransport = orderRepository.getMaxOrderTransportOnMonth(month, year);
		List<StatisticalOrder> orderCancel = orderRepository.getMaxOrderCancelOnMonth(month, year);

		int success = orderSuccess.size();
		int wait = orderWait.size();
		int transport = orderTransport.size();
		int cancel = orderCancel.size();

		StatisticalTotalOrder totalOrder = new StatisticalTotalOrder(success, cancel, wait, transport);

		return totalOrder;
	}

	
	/*
	 *  Trả về tổng đơn hàng theo năm.
	 */
	@Override
	public StatisticalTotalOrder getStatisticalTotalOrderOnYear(int year) {
		List<StatisticalOrder> orderSuccess = orderRepository.getMaxOrderSuccessOnYear(year);
		List<StatisticalOrder> orderWait = orderRepository.getMaxOrderWaitOnYear(year);
		List<StatisticalOrder> orderTransport = orderRepository.getMaxOrderTransportOnYear(year);
		List<StatisticalOrder> orderCancel = orderRepository.getMaxOrderCancelOnYear(year);

		int success = orderSuccess.size();
		int wait = orderWait.size();
		int transport = orderTransport.size();
		int cancel = orderCancel.size();

		StatisticalTotalOrder totalOrder = new StatisticalTotalOrder(success, cancel, wait, transport);

		return totalOrder;
	}

	
	/*
	 *  Tổng đơn hàng theo ngày, tháng, và năm cụ thể.
	 */
	@Override
	public StatisticalTotalOrder getStatisticalTotalOrderOnOption(int day, int month, int year) {
		StatisticalTotalOrder totalOrder = new StatisticalTotalOrder();

		if ((day == 0) && (month == 0)) {
			totalOrder = this.getStatisticalTotalOrderOnYear(year);
		} else if (day == 0) {
			totalOrder = this.getStatisticalTotalOrderOnMonth(month, year);
		} else {
			totalOrder = this.getStatisticalTotalOrderOnDay(day, month, year);
		}

		return totalOrder;
	}
	
	
	/*
	 *  Phê duyệt đơn hàng theo mã đơn hàng.
	 */
	@Override
	public void approveOrder(String id) {
		List<Order> listOrder = orderRepository.getOrderByName(id);
		for (Order list : listOrder) {
			list.setStatus("1");
			orderRepository.save(list);
		}
	}
	
	
	/*
	 *  Hủy đơn hàng theo mã đơn hàng.
	 */
	@Override
	public void cancelOrder(String id) {
		List<Order> listOrder = orderRepository.getOrderByName(id);
		for (Order list : listOrder) {
			Product product = list.getProduct();
			product.setQuality(product.getQuality() + list.getQuality());
			list.setStatus("3");
			orderRepository.save(list);
			productRepository.save(product);
		}

		Discount discount = listOrder.get(0).getDiscount();
		if (discount != null) {
			discount.setQuality(discount.getQuality() + 1);
			discountRepository.save(discount);
		}

	}
	
	
	/*
	 *  Lưu đơn hàng vào cơ sở dữ liệu.
	 */
	@Override
	public void save(Order order) {
		orderRepository.save(order);
	}

	
	/*
	 *  Đánh dấu đơn hàng đã được giao.
	 */
	@Override
	public void shippedOrder(String id) {
		List<Order> listOrder = orderRepository.getOrderByName(id);
		for (Order list : listOrder) {
			// cái Status = 2 nghĩa là đơn hàng đã được giao.
			list.setStatus("2");
			orderRepository.save(list);
		}
	}
	
	
	/*
	 *  Xóa đơn hàng khỏi cơ sở dữ liệu.
	 */
	@Override
	public void deleteOrder(String id) {
		List<Order> listOrder = orderRepository.getOrderByName(id);
		for (Order list : listOrder) {
			orderRepository.delete(list);
		}
	}

}
