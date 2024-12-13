package com.KLTN.Entity;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class District {
	
	// Thông tin id quận
    int id;

    // Thông tin tên quận
    String name;

    // Danh sách các phường thuộc quận
    List<Ward> wards;
    
}
