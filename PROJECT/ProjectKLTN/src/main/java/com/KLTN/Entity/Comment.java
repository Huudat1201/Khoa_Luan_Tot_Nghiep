package com.KLTN.Entity;

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

@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Comment {
	
	// Thông tin id comment - tự động tăng, kiểu int
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    // Thông tin người dùng - kiểu ManyToOne (Một người dùng có nhiều comment sản phẩm khác nhau).
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    // Thông tin sản phẩm - kiểu ManyToOne (Một sản phẩm có thể có nhiều lượt commnet).
    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;

    // Thông tin nội dung comment - giới hạn 500 ký tự
    @Column(name = "content", length = 500)
    String content;

    // Thông tin đánh giá sao - kiểu int
    @Column(name = "star")
    int star;

    // Thông tin ngày - giới hạn 50 ký tự
    @Column(name = "date", length = 50)
    String date;

    // Thông tin trạng thái - giới hạn 50 ký tự
    @Column(name = "status", length = 50)
    String status;

}
