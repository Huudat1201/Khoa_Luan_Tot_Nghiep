package com.KLTN.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ward {

	// Thông tin Ward Id
	private int id;

	// Thông tin tên phường
	private String name;

	// Thông tin tiền tố
	private String prefix;
	
}
