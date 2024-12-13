package com.KLTN.Service;

import java.util.List;

import com.KLTN.Entity.Contact;
import com.KLTN.Model.ContactModel;

public interface ContactService {

	// Lấy tất cả các bình luận tương tác trang web.
	List<Contact> findAll();

	// Lấy tất cả các bình luận tương tác trang web đã kiểm duyệt.
	List<Contact> getListContactChecked();

	// Lấy tất cả các bình luận tương tác trang web đang chờ xử lý.
	List<Contact> getListContactPending();

	// Tạo mới một bình luận tương tác trang web dựa trên contactModel được truyền vào.
	ContactModel createContact(ContactModel contactModel);

	// Lấy một bình luận tương tác trang web theo cái id được truyền vào.
	Contact getContactByContactId(Integer id);

	// Phê duyệt bình luận tương tác trang web dựa trên cái id được cung cấp (dành
	// cho quản trị viên).
	void approveContact(Integer id);

	// Xóa một bình luận tương tác trang web dựa trên cái id được cung cấp.
	void delete(Integer id);

}
