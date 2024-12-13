package com.KLTN.Service.Impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.KLTN.Entity.Discount;
import com.KLTN.Model.CartModel;
import com.KLTN.Service.ShoppingCartService;

@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	public static Map<Integer, CartModel> map = new HashMap<>();

	public static Map<Integer, Discount> mapDiscount = new HashMap<>();

	
	/*
	 * Danh sách các sản phẩm trong giỏ hàng dưới dạng một tập hợp (Collection) các
	 * đối tượng CartModel.
	 */
	@Override
	public Collection<CartModel> getItems() {
		return map.values();
	}
	

	/*
	 * Trả về mã giảm giá (Discount) hiện tại được áp dụng cho giỏ hàng.
	 */
	@Override
	public Discount getDiscount() {
		Discount discount = new Discount();
		Set<Integer> set = mapDiscount.keySet();
		for (Integer i : set) {
			discount = mapDiscount.get(i);
		}
		return discount;
	}

	
	/*
	 *  Số lượng loại sản phẩm trong giỏ hàng.
	 */
	@Override
	public int getCount() {
		return map.size();
	}
	

	/*
	 *  Tổng số lượng tất cả các sản phẩm trong giỏ hàng.
	 */
	@Override
	public int getCountAllProduct() {
		int count = 0;
		Set<Integer> set = map.keySet();
		for (Integer i : set) {
			count += map.get(i).getQuality();
		}
		return count;
	}

	
	/*
	 *  Tổng giá trị đơn hàng (tổng tiền) của giỏ hàng.
	 */
	@Override
	public double getAmount() {
		double amount = 0;
		Set<Integer> set = map.keySet();
		for (Integer i : set) {
			amount += map.get(i).getQuality() * map.get(i).getProduct().getPrice();
		}

		if (this.getDiscount() != null) {
			try {
				amount = amount - this.getDiscount().getPrice();
			} catch (Exception e) {

			}
			System.out.println(amount);
		}

		return amount;
	}

	
	/*
	 *  Thêm mã giảm giá vào giỏ hàng dựa trên ID của mã giảm giá và đối tượng Discount.
	 */
	@Override
	public void addDiscount(Integer id, Discount entity) {
		mapDiscount.put(id, entity);
	}

	
	/*
	 *  Thêm sản phẩm vào giỏ hàng dựa trên ID sản phẩm và đối tượng CartModel.
	 */
	@Override
	public void add(Integer id, CartModel entity) {
		if (map.get(id) != null) {
			this.update(id, entity.getQuality() + 1);
		} else {
			map.put(id, entity);
		}

	}

	
	/*
	 *  Cập nhật số lượng sản phẩm trong giỏ hàng dựa trên ID sản phẩm và số lượng mới (qty).
	 */
	@Override
	public void update(Integer id, int qty) {
		map.get(id).setQuality(qty);
	}

	
	/*
	 *  Xóa một sản phẩm khỏi giỏ hàng dựa trên ID sản phẩm.
	 */
	@Override
	public void remove(Integer id) {
		map.remove(id);
	}

	
	/*
	 *  Xóa tất cả các sản phẩm trong giỏ hàng.
	 */
	@Override
	public void clear() {
		map.clear();
	}

	
	/*
	 *  Xóa mã giảm giá khỏi giỏ hàng.
	 */
	@Override
	public void clearDiscount() {
		mapDiscount.clear();
	}

}