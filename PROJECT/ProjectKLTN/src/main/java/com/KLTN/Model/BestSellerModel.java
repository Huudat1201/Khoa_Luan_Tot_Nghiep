package com.KLTN.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.KLTN.Entity.Product;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BestSellerModel {
	
	// Thông tin về sản phẩm bán chạy nhất (Information about the best-selling product)
    @Id
    Product product;

    // Tổng số lượng sản phẩm đã bán (The total number of units sold)
    long sum;
    
}
