package com.KLTN.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.KLTN.Entity.Discount;

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
public class OrderModel {
	
	// Thông tin ID của đơn hàng (Order ID)
    @Id
    String id;
    
    // Tên sản phẩm trong đơn hàng (Product Name)
    String name;
    
    // Số lượng sản phẩm trong đơn hàng (Product Quantity)
    Long quantity;
    
    // Tổng giá trị của đơn hàng (Total Amount)
    Long total;
    
    // Ngày đặt hàng (Order Date)
    String date;
    
    // Trạng thái của đơn hàng (Order Status)
    String status;
    
    // Giảm giá áp dụng cho đơn hàng (Discount)
    Discount discount;
    
    // Tỉnh thành nơi giao hàng (Province)
    String province;
    
    // Constructor để khởi tạo thông tin đơn hàng
    public OrderModel(String id, String name, Long quantity, Long total, String date, String status, String province) {
        super();
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.total = total;
        this.date = date;
        this.status = status;
        this.province = province;
    }
	
}
