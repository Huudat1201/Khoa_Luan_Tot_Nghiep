package com.KLTN.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.KLTN.Entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
	
	// Truy vấn tìm một Contact có giá trị ID khớp với tham số uid
	// :uid là một tham số được sử dụng để truyền vào giá trị của biến id.
	@Query("SELECT c FROM Contact c WHERE c.id = :uid")
	Contact getContactByContactId(@Param("uid") Integer id);
	
	// Truy vấn danh sách các liên hệ (Contact) đang chờ xử lý có trạng thái là 0.
	@Query("SELECT c FROM Contact c WHERE c.status = 0")
	List<Contact> getListContactPending();
	
	// Truy vấn danh sách các liên hệ (Contact) đã kiểm duyệt xong có trạng thái là 1.
	@Query("SELECT c FROM Contact c WHERE c.status = 1")
	List<Contact> getListContactChecked();
	
}
