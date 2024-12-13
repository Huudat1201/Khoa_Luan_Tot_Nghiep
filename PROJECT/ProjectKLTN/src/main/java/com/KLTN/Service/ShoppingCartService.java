package com.KLTN.Service;

import java.util.Collection;

import com.KLTN.Entity.Discount;
import com.KLTN.Model.CartModel;

public interface ShoppingCartService {
	
	// Danh sách các sản phẩm trong giỏ hàng dưới dạng một tập hợp (Collection) các đối tượng CartModel.
	Collection<CartModel> getItems();

	// Trả về mã giảm giá (Discount) hiện tại được áp dụng cho giỏ hàng.
	Discount getDiscount();

	// Số lượng loại sản phẩm trong giỏ hàng.
	int getCount();

	// Tổng số lượng tất cả các sản phẩm trong giỏ hàng.
	int getCountAllProduct();

	// Tổng giá trị đơn hàng (tổng tiền) của giỏ hàng.
	double getAmount();

	// Thêm mã giảm giá vào giỏ hàng dựa trên ID của mã giảm giá và đối tượng Discount.
	void addDiscount(Integer id, Discount entity);

	// Thêm sản phẩm vào giỏ hàng dựa trên ID sản phẩm và đối tượng CartModel.
	void add(Integer id, CartModel entity);

	// Cập nhật số lượng sản phẩm trong giỏ hàng dựa trên ID sản phẩm và số lượng mới (qty).
	void update(Integer id, int qty);
	
	// Xóa một sản phẩm khỏi giỏ hàng dựa trên ID sản phẩm.
	void remove(Integer id);
	
	// Xóa tất cả các sản phẩm trong giỏ hàng. 
	void clear();
	
	// Xóa mã giảm giá khỏi giỏ hàng. 
	void clearDiscount();

}
