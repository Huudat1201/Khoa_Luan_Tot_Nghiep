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
public class AddressModel {

	// Tên đầy đủ của người dùng (Full name of the user)
	String fullName;

	// Chi tiết địa chỉ (Specific address details such as street, house number)
	String detail;

	// Số điện thoại của người dùng (User's phone number)
	String phone;

	// Tỉnh/Thành phố (Province/City information)
	String province;

	// Quận/Huyện (District information)
	String district;

	// Xã/Phường (Ward/Commune information)
	String ward;
	
	// Tắt cái địa chỉ này (không xóa khỏi csdl vì nó còn join đến bảng đơn hàng nữa).
	Boolean active;
	
}
