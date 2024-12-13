package com.KLTN.Model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DiscountModel {
	
	 // Mã định danh của mã giảm giá (Unique identifier for the discount)
    int id;

    // Tên của mã giảm giá (Name of the discount)
    String name;

    // Mã code của mã giảm giá (Code of the discount to be applied)
    String code;

    // Số tiền giảm giá (Amount of discount in terms of money)
    int price;

    // Số lượng mã giảm giá còn lại (Quantity of discounts available)
    int quality;

    // Ngày bắt đầu áp dụng mã giảm giá (Start date when the discount is applicable)
    String applyDay;

    // Ngày hết hạn của mã giảm giá (Expiration date of the discount)
    String expiration;

    // Giới hạn số tiền để áp dụng mã giảm giá (Minimum amount required to apply the discount)
    int moneyLimit;
	
}
