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
@Table(name = "discount")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Discount implements Serializable {

	// Thông tin id discount - tự động tăng, kiểu int
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	// Danh sách đơn hàng - kiểu OneToMany (Một đợt giảm giá có thể có nhiều sản phẩm khác nhau cùng áp dụng).
	@JsonIgnore
	@OneToMany(mappedBy = "discount")
	List<Order> listOrder;

	// Thông tin tên mã giảm giá - giới hạn 255 ký tự
	@Column(name = "name", length = 255)
	String name;

	// Thông tin mã giảm giá - giới hạn 50 ký tự
	@Column(name = "code", length = 50)
	String code;

	// Thông tin số tiền giảm - kiểu int
	@Column(name = "price")
	int price;

	// Thông tin số lượng mã - kiểu int
	@Column(name = "quality")
	int quality;

	// Thông tin ngày áp dụng - giới hạn 50 ký tự
	@Column(name = "applyday", length = 50)
	String Applyday;

	// Thông tin ngày hết hạn - giới hạn 50 ký tự
	@Column(name = "expiration", length = 50)
	String expiration;

	// Thông tin số tiền áp dụng - kiểu int
	@Column(name = "moneylimit")
	int Moneylimit;

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
