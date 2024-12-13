package com.KLTN.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.KLTN.Entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

	// Truy vấn một đối tượng Address dựa trên email của người dùng và ID của địa chỉ khớp với các
	// giá trị truyền vào ?1 đại diện cho tham số đầu tiên (email) và ?2 đại diện cho tham số thứ hai (id).
	@Query("SELECT a FROM Address a WHERE a.user.email = ?1 and a.id = ?2 and a.active = true")
	Address findAddressById(String email, int id);

	// Truy vấn các đối tượng Address dựa trên email của người dùng khớp với
	// giá trị truyền vào ?1 đại diện cho tham số đầu tiên được truyền vào (email).
	@Query("SELECT a FROM Address a WHERE a.user.email = ?1 and a.active = true")
	List<Address> findListAddressByEmail(String email);
}
