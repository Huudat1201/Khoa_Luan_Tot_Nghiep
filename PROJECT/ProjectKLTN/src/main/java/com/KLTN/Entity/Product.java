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
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product implements Serializable {
	
	// Thông tin id sản phẩm - tự động tăng, kiểu int
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Thông tin nhà sản xuất - kiểu ManyToOne
    @ManyToOne
    @JoinColumn(name = "manu_id")
    private Manufacturer manufacturer;

    // Thông tin danh mục - kiểu ManyToOne
    @ManyToOne
    @JoinColumn(name = "cate_id")
    private Category category;

    // Danh sách đơn hàng - kiểu OneToMany
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
    private List<Order> listOrder;

    // Danh sách yêu thích - kiểu OneToMany
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
    private List<Favorite> listFavorite;

    // Danh sách bình luận - kiểu OneToMany
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
    private List<Comment> listComment;

    // Thông tin mã sản phẩm - giới hạn 50 ký tự
    @Column(name = "code", length = 50)
    private String code;

    // Thông tin tên sản phẩm - giới hạn 255 ký tự
    @Column(name = "name", length = 255)
    private String name;

    // Thông tin giá sản phẩm - kiểu int
    @Column(name = "price")
    private int price;

    // Thông tin số lượng sản phẩm - kiểu int
    @Column(name = "quality")
    private int quality;

    // Thông tin số lượt xem - kiểu int
    @Column(name = "views")
    private int views;

    // Mô tả sản phẩm - giới hạn 500 ký tự
    @Column(name = "description", length = 500)
    private String description;

    // Thông tin các thông số - giới hạn 500 ký tự
    @Column(name = "specification", length = 500)
    private String specification;

    // Thông tin hình ảnh 1 - giới hạn 255 ký tự
    @Column(name = "image1", length = 255)
    private String image1;

    // Thông tin hình ảnh 2 - giới hạn 255 ký tự
    @Column(name = "image2", length = 255)
    private String image2;

    // Thông tin hình ảnh 3 - giới hạn 255 ký tự
    @Column(name = "image3", length = 255)
    private String image3;

    // Thông tin hình ảnh 4 - giới hạn 255 ký tự
    @Column(name = "image4", length = 255)
    private String image4;

    // Thông tin hình ảnh 5 - giới hạn 255 ký tự
    @Column(name = "image5", length = 255)
    private String image5;

    // Hiển thị sản phẩm hay không - kiểu boolean
    @Column(name = "active")
    private boolean active;

    // Thông tin giá khuyến mãi - kiểu int
    @Column(name = "sales")
    private int sales;

    // Hiển thị tên dùng để tìm kiếm - giới hạn 255 ký tự
    @Column(name = "namesearch", length = 255)
    private String Namesearch;

    // Thông tin ngày tạo - giới hạn 50 ký tự
    @Column(name = "createday", length = 50)
    private String Createday;

    // Thông tin mã người tạo - kiểu int
    @Column(name = "personcreate")
    private int Personcreate;

    // Thông tin ngày cập nhật - giới hạn 50 ký tự
    @Column(name = "updateday", length = 50)
    private String Updateday;

    // Thông tin mã người cập nhật - kiểu int
    @Column(name = "personupdate")
    private int Personupdate;

    // Thông tin ngày xóa - giới hạn 50 ký tự
    @Column(name = "deleteday", length = 50)
    private String Deleteday;

    // Thông tin mã người xóa - kiểu int
    @Column(name = "persondelete")
    private int Persondelete;

}
