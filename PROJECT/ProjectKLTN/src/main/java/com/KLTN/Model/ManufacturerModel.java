/**
 * @(#)ManufacturerModel.java 2021/09/21.
 * 
 * Copyright(C) 2021 by PHOENIX TEAM.
 * 
 * Last_Update 2021/09/21.
 * Version 1.00.
 */
package com.KLTN.Model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ManufacturerModel {
	
	// Thông tin ID của nhà sản xuất (Manufacturer ID)
    int id;
    
    // Tên của nhà sản xuất (Manufacturer Name)
    String name;
    
    // Hình ảnh banner đại diện cho nhà sản xuất (Manufacturer Banner)
    String banner;
    
    // Hình ảnh logo của nhà sản xuất (Manufacturer Logo)
    String logo;
    
    // Mô tả về nhà sản xuất (Manufacturer Description)
    String describe;
	
}
