package com.KLTN.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@SuppressWarnings("serial")
@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order implements Serializable{
	
	// Thông tin id đơn hàng - tự động tăng, kiểu int
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    // Thông tin địa chỉ người dùng - kiểu ManyToOne
    @ManyToOne
    @JoinColumn(name = "address_id")
    Address address;

    // Thông tin sản phẩm - kiểu ManyToOne
    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;
    
    // Thông tin mã giảm giá - kiểu ManyToOne
    @ManyToOne
    @JoinColumn(name = "discount_id")
    Discount discount;
    
    // Thông tin để đối chiếu - giới hạn 50 ký tự
    @Column(name = "code", length = 50)
    String code;
    
    // Thông tin số lượng - kiểu int
    @Column(name = "quality")
    int quality;
    
    // Thông tin ngày mua - giới hạn 50 ký tự
    @Column(name = "date", length = 50)
    String date;
    
    // Thông tin phương thức vận chuyển - giới hạn 50 ký tự
    @Column(name = "method", length = 50)
    String method;
    
    // Thông tin trạng thái vận chuyển - giới hạn 50 ký tự
    @Column(name = "status", length = 50)
    String status;
    
    // Thông tin ghi chú - giới hạn 255 ký tự
    @Column(name = "comment", length = 255)
    String comment;

}
