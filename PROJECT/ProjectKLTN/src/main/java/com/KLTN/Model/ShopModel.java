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
public class ShopModel {
	
	// Thông tin định danh của cửa hàng
    int id; 
    // Tên của cửa hàng
    String name; 
    // Thời gian làm việc của cửa hàng
    String time; 
    // Số điện thoại liên hệ của cửa hàng
    String phone; 
    // Số fax của cửa hàng
    String fax; 
    // Địa chỉ email của cửa hàng
    String email; 
    // Đường dẫn đến logo của cửa hàng
    String logo; 
    // Đường dẫn đến logo ở footer của cửa hàng
    String logoFooter; 
    // Địa chỉ của cửa hàng
    String address; 
    // Trạng thái hoạt động của cửa hàng (true: đang hoạt động, false: không hoạt động)
    boolean active;
	
}
