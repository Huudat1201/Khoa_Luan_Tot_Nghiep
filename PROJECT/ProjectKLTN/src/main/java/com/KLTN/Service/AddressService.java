package com.KLTN.Service;

import java.util.List;

import com.KLTN.Entity.Address;

public interface AddressService {

	// Tìm và trả về danh sách các địa chỉ dựa trên email của người dùng.
	List<Address> findListAddressByEmail(String username);

	// Lưu địa chỉ Address (Tạo mới hoặc cập nhật vào trong cơ sở dữ liệu).
	Address saveAddress(Address address);

	// Tìm và trả về một đối tượng địa chỉ (Address) dựa trên email của người dùng
	// (username) và ID của địa chỉ (id).
	Address findAddressById(String username, int id);
	
	//Trả về một đối tượng địa chỉ (Address) dựa trên ID được truyền vào.
	Address getAddressById(int id);

	// Cập nhật địa chỉ dựa trên thông tin đầu vào là addressModel và ID của địa chỉ
	// cần cập nhật.
	Address updateAddress(Address addressUpdate, Integer id);

	// Xóa đối tượng địa chỉ (Address) khỏi cơ sở dữ liệu.
	void delete(Address address);

}
