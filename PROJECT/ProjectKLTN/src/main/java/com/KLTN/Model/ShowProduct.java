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
public class ShowProduct {
	
	 // Đối tượng sản phẩm chứa thông tin chi tiết về sản phẩm
    Product product;
    
    // Tổng số sao đánh giá của sản phẩm
    int totalStar;
    
}
