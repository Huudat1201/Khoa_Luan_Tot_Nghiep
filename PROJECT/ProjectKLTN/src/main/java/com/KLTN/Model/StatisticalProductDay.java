package com.KLTN.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

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
public class StatisticalProductDay {

	 // Mã sản phẩm (Product Code)
    @Id
    String code; 

    // Tên sản phẩm (Product Name)
    String name; 

    // Giá sản phẩm (Product Price)
    int price; 

    // Số lượng sản phẩm (Product Quantity)
    int quantity; 

    // Số lượng sản phẩm đã bán (Total Sold Quantity)
    long selled; 
    
}
