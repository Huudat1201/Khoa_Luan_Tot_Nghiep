package com.KLTN.Service.Impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.KLTN.Entity.Discount;
import com.KLTN.Entity.User;
import com.KLTN.Model.DiscountModel;
import com.KLTN.Repository.DiscountRepository;
import com.KLTN.Repository.UserRepository;
import com.KLTN.Service.DiscountService;

@Service
public class DiscountServiceImpl implements DiscountService {
	
	@Autowired
	private DiscountRepository discountRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	MailerServiceImpl mailerService;

	
	/*
	 *  Danh sách các mã giảm giá.
	 */
	@Override
	public List<Discount> findAll() {
		return discountRepository.getListDiscount();
	}
	
	
	/*
	 *  Danh sách các mã giảm giá còn khả dụng.
	 */
	@Override
	public List<Discount> getListDiscountAvailable() {
		return discountRepository.getListDiscountAvailable();
	}
	
	
	/*
	 *  Tạo mới một mã giảm giá dựa trên đối tượng discountModel (dành cho quản trị viên).
	 */
	@Override
	public DiscountModel createDiscount(DiscountModel discountModel) {
		// Lấy đối tượng người dùng đang đăng nhập từ Security Context.
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		// Tạo một đối tượng Timestamp với thời gian hiện tại, được sử dụng để ghi lại thời gian tạo mới Discount.
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User temp = userRepository.findUserByEmail(username);

		// Tiến hành tạo mới Discount.
		Discount discount = new Discount();
		discount.setName(discountModel.getName());
		discount.setCode(discountModel.getCode());
		discount.setPrice(discountModel.getPrice());
		discount.setApplyday(discountModel.getApplyDay());
		discount.setExpiration(discountModel.getExpiration());
		discount.setQuality(discountModel.getQuality());
		discount.setMoneylimit(discountModel.getMoneyLimit());
		discount.setPersoncreate(temp.getId());
		discount.setCreateday(timestamp.toString());
		discountRepository.save(discount);
		return discountModel;
	}
	

	/*
	 *  Lấy ra một mã giảm giá dựa trên id được cung cấp.
	 */
	@Override
	public DiscountModel getOneDiscountById(Integer id) {
		Discount discount = discountRepository.findById(id).get();
		DiscountModel discountModel = new DiscountModel();
		discountModel.setName(discount.getName());
		discountModel.setPrice(discount.getPrice());
		discountModel.setCode(discount.getCode());
		discountModel.setApplyDay(discount.getApplyday());
		discountModel.setExpiration(discount.getExpiration());
		discountModel.setMoneyLimit(discount.getMoneylimit());
		discountModel.setQuality(discount.getQuality());
		return discountModel;
	}

	/*
	 *  Lấy ra một mã giảm giá dựa trên code được truyền vào (chức năng dành cho người mua hàng).
	 */
	@Override
	public Discount getDiscountByCode(String code) {
		return discountRepository.getDiscountByCode(code);
	}

	
	/*
	 *  Cập nhật mã giảm giá (chức năng dành cho quản trị viên).
	 */
	@Override
	public DiscountModel updateDiscount(DiscountModel discountModel) {
		// Lấy đối tượng người dùng đang đăng nhập từ Security Context.
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		// Tạo một đối tượng Timestamp với thời gian hiện tại, được sử dụng để ghi lại thời gian cập nhật mã giảm giá. 
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User temp = userRepository.findUserByEmail(username);

		// Tiến hành cập nhật mã giảm giá.
		Discount discount = discountRepository.findById(discountModel.getId()).get();
		discount.setName(discountModel.getName());
		discount.setCode(discountModel.getCode());
		discount.setPrice(discountModel.getPrice());
		discount.setApplyday(discountModel.getApplyDay());
		discount.setExpiration(discountModel.getExpiration());
		discount.setQuality(discountModel.getQuality());
		discount.setMoneylimit(discountModel.getMoneyLimit());
		discount.setUpdateday(timestamp.toString());
		discount.setPersonupdate(temp.getId());
		discountRepository.save(discount);
		return discountModel;
	}
	

	/*
	 *  Cập nhật thông tin về chất lượng của đối tượng giảm giá.
	 */
	@Override
	public void updateQuality(Discount discount) {
		if (discount != null) {
			discount.setQuality(discount.getQuality() - 1);
			discountRepository.save(discount);
		}
	}

	
	/*
	 *  Gửi mã giảm giá cho người dùng (dựa trên cái discountId và User được truyền vào).
	 */
	@Override
	public User sendCodeDiscount(Integer discountId, User user) {
		Discount discount = discountRepository.findById(discountId).get();

		String[] applyDay = discount.getApplyday().split("-");
		String resultApplyDay = applyDay[2] + "/" + applyDay[1] + "/" + applyDay[0];

		String[] expiration = discount.getExpiration().split("-");
		String resultExpiration = expiration[2] + "/" + expiration[1] + "/" + expiration[0];

		mailerService.queue(user.getEmail(), " DatHauSmartFarm.com Thông Tin Khuyến Mãi!",
				"Xin chào bạn " + user.getFullname() + ",<br>"
						+ "DatHauSmartFarm.com xin thông báo đến bạn chương trình. " + discount.getName()
						+ " khi bạn nhập mã <b>" + discount.getCode() + "</b>." + "<br>" + "Thời gian áp dụng từ ngày "
						+ resultApplyDay + " đến ngày " + resultExpiration + "<br>" + "Số tiền giảm "
						+ discount.getPrice() + "đ<br>" + "Số tiền áp dụng trên " + discount.getMoneylimit() + "đ<br>"
						+ "<br><br>" + "Xin chân thành cảm ơn đã sử dụng dịch vụ,<br>" + "DatHauSmartFarm.com");
		return user;
	}
	

	/*
	 *  Xóa một mã giảm giá (chức năng dành cho quản trị viên).
	 */
	@Override
	public void delete(Integer id) {
		// Lấy đối tượng người dùng đang đăng nhập từ Security Context.
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		
		// Tạo một đối tượng Timestamp với thời gian hiện tại, được sử dụng để ghi lại thời gian xóa mã giảm giá. 
		User temp = userRepository.findUserByEmail(username);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		// Tiến hành xóa mã giảm giá, nhưng lưu chúng lại csdl để đối chiếu lịch sử thanh toán.
		Discount discount = discountRepository.findById(id).get();
		discount.setPersondelete(temp.getId());
		discount.setDeleteday(timestamp.toString());
		discountRepository.save(discount);
	}

}
