package com.KLTN.Service.Impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KLTN.Entity.Contact;
import com.KLTN.Model.ContactModel;
import com.KLTN.Repository.ContactRepository;
import com.KLTN.Repository.UserRepository;
import com.KLTN.Service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {
	
	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	UserRepository usercontactRepository;

	@Autowired
	private MailerServiceImpl mailerService;

	
	/*
	 *  Lấy tất cả các bình luận tương tác trang web.
	 */
	@Override
	public List<Contact> findAll() {
		return contactRepository.findAll();
	}

	
	/*
	 *  Lấy tất cả các bình luận tương tác trang web đã kiểm duyệt.
	 */
	@Override
	public List<Contact> getListContactChecked() {
		return contactRepository.getListContactChecked();
	}

	
	/*
	 *  Lấy tất cả các bình luận tương tác trang web đang chờ xử lý.
	 */
	@Override
	public List<Contact> getListContactPending() {
		return contactRepository.getListContactPending();
	}

	
	/*
	 *  Tạo mới một bình luận tương tác trang web dựa trên contactModel được truyền vào.
	 */
	@Override
	public ContactModel createContact(ContactModel contactModel) {
		// Thời gian đánh giá trang web của người dùng.
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = formatter.format(date);

		// Tạo mới một đánh giá.
		Contact contact = new Contact();
		contact.setName(contactModel.getName());
		contact.setEmail(contactModel.getEmail());
		contact.setContent(contactModel.getContent());
		String name = contactModel.getName();
		contact.setDate(strDate);
		contact.setStatus("0");
		contactRepository.save(contact);
		
		// Gửi email cảm ơn đến người dùng thông qua dịch vụ.
		mailerService.queue(contactModel.getEmail(), "Thông Báo DatHauSmartFarm.com", "Kính chào " + name + ",<br>"
				+ "Đại diện  DatHauSmartFarm.com shop xin chân thành cảm ơn bạn đã ghé qua và để lại đánh giá ý kiến cá nhân về shop. "
				+ "Ý kiến đóng góp của bạn shop sẽ ghi nhận để góp phần phát triển shop hơn.<br>" + "<br><br>"
				+ "Trân trọng,<br>" + "DatHauSmartFarm.com");
		return contactModel;
	}

	
	/*
	 *  Lấy một bình luận tương tác trang web theo cái id được truyền vào.
	 */
	@Override
	public Contact getContactByContactId(Integer id) {
		return contactRepository.getContactByContactId(id);
	}

	
	/*
	 *  Phê duyệt bình luận tương tác trang web dựa trên cái id được cung cấp (dành cho quản trị viên).
	 */
	@Override
	public void approveContact(Integer id) {
		Contact contact = contactRepository.findById(id).get();
		contact.setStatus("1");
		contactRepository.save(contact);
	}

	
	/*
	 *  Xóa một bình luận tương tác trang web dựa trên cái id được cung cấp.
	 */
	@Override
	public void delete(Integer id) {
		Contact contact = contactRepository.findById(id).get();
		contactRepository.delete(contact);
	}

}
