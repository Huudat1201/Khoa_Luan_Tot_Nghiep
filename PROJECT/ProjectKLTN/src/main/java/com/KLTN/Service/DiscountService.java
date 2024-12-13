package com.KLTN.Service;

import java.util.List;

import com.KLTN.Entity.Discount;
import com.KLTN.Entity.User;
import com.KLTN.Model.DiscountModel;

public interface DiscountService {

	// Danh sách các mã giảm giá.
	List<Discount> findAll();
	
	// Danh sách các mã giảm giá còn khả dụng.
	List<Discount> getListDiscountAvailable();
	
	// Tạo mới một mã giảm giá dựa trên đối tượng discountModel (dành cho quản trị viên).
	DiscountModel createDiscount(DiscountModel discountModel);
	
	// Lấy ra một mã giảm giá dựa trên id được cung cấp.
	DiscountModel getOneDiscountById(Integer id);
	
	// Lấy ra một mã giảm giá dựa trên code được truyền vào (chức năng dành cho người mua hàng).
	Discount getDiscountByCode(String code);
	
	// Cập nhật mã giảm giá (chức năng dành cho quản trị viên).
	DiscountModel updateDiscount(DiscountModel discountModel);

	// Cập nhật thông tin về chất lượng của đối tượng giảm giá.
	void updateQuality(Discount discount);
	
	// Gửi mã giảm giá cho người dùng (dựa trên cái discountId và User được truyền vào).
	User sendCodeDiscount(Integer discountId, User user);
	
	// Xóa một mã giảm giá (chức năng dành cho quản trị viên).
	void delete(Integer id);
	
}
