package com.KLTN.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@SuppressWarnings("serial")
@Entity
@Table(name = "informationshop")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InformationShop implements Serializable {

	// Thông tin id cửa hàng - tự động tăng, kiểu int
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	// Thông tin tên cửa hàng - giới hạn 255 ký tự
	@Column(name = "name", length = 255)
	String name;

	// Thông tin logo - giới hạn 255 ký tự
	@Column(name = "logo", length = 255)
	String logo;

	// Thông tin số điện thoại - giới hạn 15 ký tự
	@Column(name = "phone", length = 15)
	String phone;

	// Thông tin fax - giới hạn 50 ký tự
	@Column(name = "fax", length = 50)
	String fax;

	// Thông tin email - giới hạn 255 ký tự
	@Column(name = "email", length = 255)
	String email;

	// Thông tin logo footer - giới hạn 255 ký tự
	@Column(name = "logofooter", length = 255)
	String Logofooter;

	// Thông tin địa chỉ cửa hàng - giới hạn 255 ký tự
	@Column(name = "address", length = 255)
	String address;

	// Thông tin thời gian mở cửa - giới hạn 50 ký tự
	@Column(name = "timeopen", length = 50)
	String Timeopen;

	// Thông tin trạng thái kích hoạt - kiểu boolean
	@Column(name = "active")
	boolean active;

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
