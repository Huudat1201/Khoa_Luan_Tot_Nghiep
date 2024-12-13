package com.KLTN.Service.Impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.KLTN.Entity.Manufacturer;
import com.KLTN.Entity.User;
import com.KLTN.Model.ManufacturerModel;
import com.KLTN.Repository.ManufacturerRepository;
import com.KLTN.Repository.UserRepository;
import com.KLTN.Service.ManufacturerService;

@Service
public class ManufacturerServiceImpl implements ManufacturerService{
	
	@Autowired
	private ManufacturerRepository manufacturerRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	/*
	 *  Danh sách các nhà sản xuất.
	 */
	@Override
	public List<Manufacturer> findAll() {
		return manufacturerRepository.getListManufacturer();
	}
	
	/*
	 *  Tạo mới một nhà sản xuất.
	 */
	@Override
	public ManufacturerModel createManufacturer(ManufacturerModel manufacturerModel) {
		// Lấy thông tin tên đăng nhập của người dùng hiện tại trong ứng dụng Spring Security.
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		
		// Tạo một đối tượng Timestamp với thời gian hiện tại, được sử dụng để ghi lại thời gian tạo mới nhà sản xuất.
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User temp = userRepository.findUserByEmail(username);
		
		// Tiến hành tạo mới nhà sản xuất.
		Manufacturer manufacturer = new Manufacturer();
		manufacturer.setName(manufacturerModel.getName());
		manufacturer.setLogo(manufacturerModel.getLogo());
		manufacturer.setBanner(manufacturerModel.getBanner());
		manufacturer.setDescription(manufacturerModel.getDescribe());
		manufacturer.setPersoncreate(temp.getId());
		manufacturer.setCreateday(timestamp.toString());
		manufacturerRepository.save(manufacturer);
		return manufacturerModel;
	}

	/*
	 *  Lấy ra thông tin một nhà sản xuất.
	 */
	@Override
	public ManufacturerModel getOneManufacturerById(Integer id) {
		Manufacturer manufacturer = manufacturerRepository.findById(id).get();
		ManufacturerModel manufacturerModel = new ManufacturerModel();
		manufacturerModel.setName(manufacturer.getName());
		manufacturerModel.setLogo(manufacturer.getLogo());
		manufacturerModel.setBanner(manufacturer.getBanner());
		manufacturerModel.setDescribe(manufacturer.getDescription());
		return manufacturerModel;
	}
	
	/*
	 *  Cập nhật thông tin nhà sản xuất.
	 */
	@Override
	public ManufacturerModel updateManufacturer(ManufacturerModel manufacturerModel) {
		// Lấy thông tin tên đăng nhập của người dùng hiện tại trong ứng dụng Spring Security.
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		
		// Tạo một đối tượng Timestamp với thời gian hiện tại, được sử dụng để ghi lại thời gian chỉnh sửa cửa hàng.
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User temp = userRepository.findUserByEmail(username);
		
		// Tiến hành cập nhật nhà sản xuất.
		Manufacturer manufacturer = manufacturerRepository.findById(manufacturerModel.getId()).get();
		manufacturer.setName(manufacturerModel.getName());
		manufacturer.setLogo(manufacturerModel.getLogo());
		manufacturer.setBanner(manufacturerModel.getBanner());
		manufacturer.setDescription(manufacturerModel.getDescribe());
		manufacturer.setUpdateday(timestamp.toString());
		manufacturer.setPersonupdate(temp.getId());
		manufacturerRepository.save(manufacturer);
		return manufacturerModel;
	}
	
	/*
	 *  Xóa một nhà sản xuất theo id được cung cấp.
	 */
	@Override
	public void delete(Integer id) {
		// Lấy thông tin tên đăng nhập của người dùng hiện tại trong ứng dụng Spring Security.
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		
		// Tạo một đối tượng Timestamp với thời gian hiện tại, được sử dụng để ghi lại thời gian xóa cửa hàng.
		User temp = userRepository.findUserByEmail(username);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		// Không xóa cửa hàng hoàn toàn mà tiến hành setDeleteday = null để đối chiếu lịch sử.
		Manufacturer manufacturer = manufacturerRepository.findById(id).get();
		manufacturer.setPersondelete(temp.getId());
		manufacturer.setDeleteday(timestamp.toString());
		manufacturerRepository.save(manufacturer);
	}

}
