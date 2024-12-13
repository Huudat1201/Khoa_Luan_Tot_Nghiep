package com.KLTN.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.KLTN.Entity.Address;
import com.KLTN.Entity.User;
import com.KLTN.Repository.AddressRepository;
import com.KLTN.Repository.UserRepository;
import com.KLTN.Service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private UserRepository userRepository;

	/*
	 * Tìm và trả về danh sách các địa chỉ dựa trên email của người dùng.
	 */
	@Override
	public List<Address> findListAddressByEmail(String username) {
		return addressRepository.findListAddressByEmail(username);
	}

	/*
	 * Trả về một đối tượng địa chỉ (Address) dựa trên ID được truyền vào.
	 */
	@Override
	public Address getAddressById(int id) {
		return addressRepository.findById(id).get();
	}

	@Override
	public Address findAddressById(String username, int id) {
		return addressRepository.findAddressById(username, id);
	}

	// Lưu xuống một địa chỉ.
	@Override
	public Address saveAddress(Address addressCreate) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		User temp = userRepository.findUserByEmail(username);
		Address address = new Address();
		address.setFullname(addressCreate.getFullname());
		address.setPhone(addressCreate.getPhone());
		address.setDetail(addressCreate.getDetail());
		address.setProvince(addressCreate.getProvince());
		address.setDistrict(addressCreate.getDistrict());
		address.setWard(addressCreate.getWard());
		address.setUser(temp);
		address.setActive(true);
		address.setActive(true);
		
		return addressRepository.save(address);
	}

	/*
	 * Cập nhật địa chỉ dựa trên thông tin đầu vào là addressModel và ID của địa chỉ
	 * cần cập nhật.
	 */
	@Override
	public Address updateAddress(Address addressUpdate, Integer id) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		User temp = userRepository.findUserByEmail(username);
		Address address = addressRepository.findAddressById(username, id);

		address.setFullname(addressUpdate.getFullname());
		address.setPhone(addressUpdate.getPhone());
		address.setDetail(addressUpdate.getDetail());
		address.setProvince(addressUpdate.getProvince());
		address.setDistrict(addressUpdate.getDistrict());
		address.setWard(addressUpdate.getWard());
		address.setUser(temp);
		address.setActive(true);
		return addressRepository.save(address);
	}

	/*
	 * Xóa đối tượng địa chỉ (Address) khỏi cơ sở dữ liệu.
	 */
	@Override
	public void delete(Address address) {
		address.setActive(false);
		addressRepository.save(address);
	}

}
