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
@Table(name = "blogs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Blog implements Serializable {

	// Thông tin id blog - tự động tăng, kiểu int
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	// Thông tin banner - giới hạn 255 ký tự
	@Column(name = "banner", length = 255)
	String Banner;

	// Thông tin tên tìm kiếm - giới hạn 255 ký tự
	@Column(name = "namesearch", length = 255)
	String Namesearch;

	// Thông tin tiêu đề - giới hạn 255 ký tự
	@Column(name = "title", length = 255)
	String title;

	// Thông tin nội dung - kiểu TEXT
	@Column(name = "content", length = 4000)
	String content;

	// Thông tin logo - giới hạn 255 ký tự
	@Column(name = "logo", length = 255)
	String logo;

	// Thông tin mô tả - kiểu TEXT
	@Column(name = "description", length = 4000)
	String description;

	// Thông tin trạng thái - kiểu boolean
	@Column(name = "active")
	boolean active;

	// Thông tin ngày tạo - giới hạn 50 ký tự
	@Column(name = "createday", length = 50)
	String Createday;

	// Thông tin ngày tải lên - giới hạn 50 ký tự
	@Column(name = "uploadday", length = 50)
	String Uploadday;

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
