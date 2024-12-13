package com.KLTN.Service.Impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.KLTN.Entity.InformationShop;
import com.KLTN.Entity.User;
import com.KLTN.Model.ShopModel;
import com.KLTN.Repository.InformationShopRepository;
import com.KLTN.Repository.UserRepository;
import com.KLTN.Service.InformationShopService;

@Service
public class InformationShopServiceImpl implements InformationShopService {

	@Autowired
	private InformationShopRepository informationRepository;

	@Autowired
	private UserRepository userRepository;

	
	/*
	 * Trả về danh sách tất cả các cửa hàng.
	 */
	@Override
	public List<InformationShop> findAll() {
		return informationRepository.getListInformationShop();
	}

	
	/*
	 * Trả về thông tin của một cửa hàng.
	 */
	@Override
	public InformationShop getOneInformationShop() {
		InformationShop informationShop = informationRepository.getOneInformationShop();
		String phone = informationShop.getPhone().substring(0, 3) + "-" + informationShop.getPhone().substring(3, 6)
				+ "-" + informationShop.getPhone().substring(6);
		String fax = "+82 " + informationShop.getFax().substring(1, 4) + " " + informationShop.getFax().substring(4, 7)
				+ " " + informationShop.getFax().substring(7);
		informationShop.setPhone(phone);
		informationShop.setFax(fax);
		return informationShop;
	}

	
	/*
	 * Tạo một cửa hàng mới.
	 */
	@Override
	public ShopModel createInformationShop(ShopModel shopModel) {
		// Lấy thông tin tên đăng nhập của người dùng hiện tại trong ứng dụng Spring Security.
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		// Tạo một đối tượng Timestamp với thời gian hiện tại, được sử dụng để ghi lại thời gian tạo mới cửa hàng.
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User temp = userRepository.findUserByEmail(username);

		// Tiến hành tạo mới cửa hàng.
		InformationShop informationShop = new InformationShop();
		informationShop.setName(shopModel.getName());
		informationShop.setTimeopen(shopModel.getTime());
		informationShop.setLogo(shopModel.getLogo());
		informationShop.setLogofooter(shopModel.getLogoFooter());
		informationShop.setPhone(shopModel.getPhone());
		informationShop.setFax(shopModel.getFax());
		informationShop.setEmail(shopModel.getEmail());
		informationShop.setAddress(shopModel.getAddress());
		informationShop.setCreateday(timestamp.toString());
		informationShop.setPersoncreate(temp.getId());
		informationRepository.save(informationShop);
		return shopModel;
	}

	
	/*
	 * Cập nhật trạng thái hoạt động của cửa hàng.
	 */
	@Override
	public ShopModel updateActive(ShopModel shopModel) {
		List<InformationShop> list = informationRepository.findAll();
		for (InformationShop infor : list) {
			if (infor.getId() != shopModel.getId()) {
				infor.setActive(false);
			} else {
				infor.setActive(true);
			}
			informationRepository.save(infor);
		}

		return shopModel;
	}

	
	/*
	 * Trả về thông tin một cửa hàng dựa trên ID.
	 */
	@Override
	public ShopModel getOneShopById(Integer id) {
		InformationShop informationShop = informationRepository.findById(id).get();
		ShopModel shopModel = new ShopModel();
		shopModel.setAddress(informationShop.getAddress());
		shopModel.setEmail(informationShop.getEmail());
		shopModel.setFax(informationShop.getFax());
		shopModel.setPhone(informationShop.getPhone());
		shopModel.setLogo(informationShop.getLogo());
		shopModel.setLogoFooter(informationShop.getLogofooter());
		shopModel.setName(informationShop.getName());
		shopModel.setTime(informationShop.getTimeopen());
		return shopModel;
	}

	
	/*
	 * Cập nhật thông tin của một cửa hàng.
	 */
	@Override
	public ShopModel updateInformation(ShopModel shopModel) {
		// Lấy thông tin tên đăng nhập của người dùng hiện tại trong ứng dụng Spring Security.
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		// Tạo một đối tượng Timestamp với thời gian hiện tại, được sử dụng để ghi lại thời gian cập nhật cửa hàng.
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User temp = userRepository.findUserByEmail(username);

		// Tiến hành cập nhật thông tin cửa hàng.
		InformationShop informationShop = informationRepository.findById(shopModel.getId()).get();
		informationShop.setAddress(shopModel.getAddress());
		informationShop.setEmail(shopModel.getEmail());
		informationShop.setFax(shopModel.getFax());
		informationShop.setLogo(shopModel.getLogo());
		informationShop.setLogofooter(shopModel.getLogoFooter());
		informationShop.setName(shopModel.getName());
		informationShop.setTimeopen(shopModel.getTime());
		informationShop.setPhone(shopModel.getPhone());
		informationShop.setUpdateday(timestamp.toString());
		informationShop.setPersonupdate(temp.getId());
		informationRepository.save(informationShop);
		return shopModel;
	}

	
	/*
	 * Xóa một cửa hàng dựa trên ID.
	 */
	@Override
	public void delete(Integer id) {
		// Lấy thông tin tên đăng nhập của người dùng hiện tại trong ứng dụng Spring Security.
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		// Tạo một đối tượng Timestamp với thời gian hiện tại, được sử dụng để ghi lại thời gian xóa cửa hàng.
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User temp = userRepository.findUserByEmail(username);

		// Tiến hành xóa cửa hàng nhưng không xóa đối tượng hoàn toàn để đối chiếu lưu lại lịch sử cửa hàng.
		InformationShop informationShop = informationRepository.findById(id).get();
		informationShop.setDeleteday(timestamp.toString());
		informationShop.setPersondelete(temp.getId());
		informationRepository.save(informationShop);
	}

}
