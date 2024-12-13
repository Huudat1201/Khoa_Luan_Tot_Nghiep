package com.KLTN.Model;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DetailOrder {
	
	// Mã định danh của đơn hàng (Unique identifier for the order)
    String id;

    // Ngày đặt hàng (Date the order was placed)
    String date;

    // Phương thức thanh toán (Payment method used for the order)
    String method;

    // Họ và tên của người nhận hàng (Full name of the person receiving the order)
    String fullName;

    // Địa chỉ giao hàng chi tiết (Detailed shipping address)
    String address;

    // Số điện thoại của người nhận hàng (Phone number of the person receiving the order)
    String phone;

    // Tỉnh của địa chỉ giao hàng (Province of the shipping address)
    String province;

    // Quận/huyện của địa chỉ giao hàng (District of the shipping address)
    String district;

    // Phường/xã của địa chỉ giao hàng (Ward of the shipping address)
    String ward;

    // Tổng tiền hàng trước giảm giá (Subtotal before any discounts)
    int subTotal;

    // Số tiền được giảm giá (Discount amount applied to the order)
    int discount;

    // Tổng số tiền phải thanh toán sau giảm giá (Total amount to be paid after discount)
    int total;

    // Ghi chú về đơn hàng (Additional comments or notes about the order)
    String comment;

    // Danh sách các sản phẩm trong giỏ hàng (List of products in the order)
    List<CartModel> listOrder;
	
}
