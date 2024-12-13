package com.KLTN.Entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Province {

	// Thông tin mã tỉnh - giới hạn 10 ký tự
	private String code;

	// Thông tin ID tỉnh - kiểu Integer
	private Integer id;

	// Thông tin tên tỉnh - giới hạn 100 ký tự
	private String name;

	// Danh sách các quận - liên kết nhiều đến một
	private List<District> districts;

}
