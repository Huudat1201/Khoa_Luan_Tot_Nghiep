package com.KLTN.Entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "manufactures")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Manufacturer implements Serializable {
	
	// Thông tin id nhà sản xuất - tự động tăng, kiểu int
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    // Danh sách sản phẩm - kiểu OneToMany
    @JsonIgnore
    @OneToMany(mappedBy = "manufacturer")
    List<Product> listProduct;

    // Thông tin tên nhà sản xuất - giới hạn 255 ký tự
    @Column(name = "name", length = 255)
    String name;

    // Thông tin banner - giới hạn 255 ký tự
    @Column(name = "banner", length = 255)
    String banner;

    // Thông tin hình đại diện - giới hạn 255 ký tự
    @Column(name = "logo", length = 255)
    String logo;

    // Thông tin mô tả - giới hạn 500 ký tự
    @Column(name = "description", length = 500)
    String description;

    // Thông tin ngày tạo - giới hạn 50 ký tự
    @Column(name = "createday", length = 50)
    String Createday;

    // Thông tin mã người tạo - kiểu int
    @Column(name = "personcreate")
    int Personcreate;

    // Thông tin ngày cập nhật - giới hạn 50 ký tự
    @Column(name = "updateday", length = 50)
    String Updateday;

    // Thông tin mã người cập nhật - kiểu int
    @Column(name = "personupdate")
    int Personupdate;

    // Thông tin ngày xóa - giới hạn 50 ký tự
    @Column(name = "deleteday", length = 50)
    String Deleteday;

    // Thông tin mã người xóa - kiểu int
    @Column(name = "persondelete")
    int Persondelete;

}
