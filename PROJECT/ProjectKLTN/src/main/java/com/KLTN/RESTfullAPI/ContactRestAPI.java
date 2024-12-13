package com.KLTN.RESTfullAPI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.KLTN.Entity.Contact;
import com.KLTN.Model.ContactModel;
import com.KLTN.Service.ContactService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/contact")
public class ContactRestAPI {

	@Autowired
	private ContactService contactService;

	// lấy tất cả các đánh giá web.
	@GetMapping
	public List<Contact> getAll() {
		return contactService.getListContactChecked();
	}

	// Lấy danh sách đã được kiểm duyệt (Chức năng dành cho admin).
	@GetMapping("/approved")
	public List<Contact> getListContactChecked() {
		return contactService.getListContactChecked();
	}

	// Lấy danh sách tương tác chưa được kiểm duyệt (Chức năng dành cho admin).
	@GetMapping("/pending")
	public List<Contact> getListContactPending() {
		return contactService.getListContactPending();
	}

	// Lấy một bình luận chờ kiểm duyệt.
	@GetMapping("/pending/{id}")
	public Contact getContactByContactId(@PathVariable("id") Integer id) {
		return contactService.getContactByContactId(id);
	}

	// tạo mới bình luận.
	@PostMapping("/form")
	public ContactModel create(@RequestBody ContactModel contactModel) {
		return contactService.createContact(contactModel);
	}

	// Chỉnh sửa đánh giá trang web.
	@PutMapping("/form/approve/{id}")
	public void approve(@PathVariable("id") Integer id) {
		contactService.approveContact(id);
	}

	// Xóa một đánh giá trang web.
	@DeleteMapping("/form/delete/{id}")
	public void delete(@PathVariable("id") Integer id) {
		contactService.delete(id);
	}

}
