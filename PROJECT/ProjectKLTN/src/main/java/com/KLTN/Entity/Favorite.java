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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@SuppressWarnings("serial")
@Entity
@Table(name = "favorites")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Favorite implements Serializable {
	
	// Thông tin id yêu thích - tự động tăng, kiểu int
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Thông tin người dùng liên kết với bảng User - kiểu ManyToOne (Một người có thể thích nhiều sản phẩm khác nhau).
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Thông tin sản phẩm liên kết với bảng products - kiểu ManyToOne (Một sản phẩm có thể có nhiều lượt thích).
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    // Thông tin ngày thêm vào yêu thích - giới hạn 50 ký tự
    @Column(name = "date", length = 50)
    private String date;

}
