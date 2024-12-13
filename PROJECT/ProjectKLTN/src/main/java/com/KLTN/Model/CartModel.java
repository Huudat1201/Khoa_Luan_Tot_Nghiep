package com.KLTN.Model;

import com.KLTN.Entity.Product;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartModel {
	
	 // Mã định danh của giỏ hàng (Unique identifier for the cart item)
    int id;

    // Thông tin về sản phẩm trong giỏ hàng (Product information in the cart)
    Product product;

    // Số lượng sản phẩm trong giỏ hàng (Quantity of the product in the cart)
    int quality;
    
}
