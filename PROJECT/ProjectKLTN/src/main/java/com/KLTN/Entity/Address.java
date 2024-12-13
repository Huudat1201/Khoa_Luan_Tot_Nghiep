package com.KLTN.Entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@SuppressWarnings("serial")
@Entity
@Table(name = "address")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Address implements Serializable {
	
	 // Thông tin Address ID - khóa chính, tự động tăng
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    // Thông tin người dùng - quan hệ nhiều-nhiều với bảng User
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id") // Liên kết với cột User_Id trong bảng User
    User user;

    // Danh sách các đơn hàng liên quan đến địa chỉ
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "address")
    List<Order> listOrder;

    // Thông tin họ và tên - giới hạn 100 ký tự
    @Column(name = "fullname", length = 100)
    String Fullname;

    // Thông tin số điện thoại - giới hạn 15 ký tự
    @Column(name = "phone", length = 15)
    String phone;

    // Thông tin tỉnh - giới hạn 50 ký tự
    @Column(name = "province", length = 50)
    String province;

    // Thông tin huyện - giới hạn 50 ký tự
    @Column(name = "district", length = 50)
    String district;

    // Thông tin xã - giới hạn 50 ký tự
    @Column(name = "ward", length = 50)
    String ward;

    // Thông tin địa chỉ chi tiết - giới hạn 255 ký tự
    @Column(name = "detail", length = 255)
    String detail;
    
    @Column(name = "active")
    Boolean active;
 
}
