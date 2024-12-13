package com.KLTN.Service.Impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.KLTN.Entity.User;
import com.KLTN.Model.ChangePassModel;
import com.KLTN.Model.InformationModel;
import com.KLTN.Repository.UserRepository;
import com.KLTN.Service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	
	/*
	 *  Danh sách tất cả người dùng.
	 */
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	
	/*
	 *  Danh sách người dùng không có vai trò Admin.
	 */
	@Override
	public List<User> findAllUserNotRoleAdmin() {
		List<User> listUser = userRepository.findAllUserNotRoleAdmin();
		return listUser;
	}

	
	/*
	 *  Danh sách tất cả người dùng đã kích hoạt tài khoản.
	 */
	@Override
	public List<User> findAllUserAnable() {
		return userRepository.findAllUserAnable();
	}

	
	/*
	 *  Danh sách người dùng đã đăng ký nhận thông báo.
	 */
	@Override
	public List<User> getListUserEnableSubscribe() {
		return userRepository.getListUserEnableSubscribe();
	}

	
	/*
	 *  Lưu một người dùng mới.
	 */
	@Override
	public User create(User user) {
		return userRepository.save(user);
	}

	
	/*
	 *  Trả về người dùng dựa trên ID.
	 */
	@Override
	public User findById(Integer id) {
		return userRepository.findById(id).get();
	}

	
	/*
	 *  Trả về người dùng dựa trên email.
	 */
	@Override
	public User findUserByEmail(String email) {
		return userRepository.findUserByEmail(email);
	}

	
	/*
	 *  Trả về thông tin tài khoản người dùng hiện tại.
	 */
	@Override
	public InformationModel getUserAccount() {
		// Lấy thông tin tên đăng nhập của người dùng hiện tại trong ứng dụng Spring Security.
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		// Tìm đổi tượng uername.
		User user = userRepository.findUserByEmail(username);

		// Tiến hành lấy ra một đối tượng information.
		InformationModel information = new InformationModel();
		information.setPassword(user.getPassword());
		information.setFullName(user.getFullname());
		information.setEmail(user.getEmail());
		information.setBirthday(user.getBirthday());
		information.setGender(user.getSex());
		information.setNews(user.getSubscribe());

		return information;
	}

	
	/*
	 *  Tạo một người dùng mới và trả về thông tin người dùng đã tạo.
	 */
	@Override
	public InformationModel createUser(InformationModel informationModel) {
		// Tạo một đối tượng Timestamp với thời gian hiện tại, được sử dụng để ghi lại thời gian tạo mới người dùng.
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		// Tiến hành tạo người dùng mới.
		User user = new User();
		user.setEmail(informationModel.getEmail());
		user.setFullname(informationModel.getFullName());
		user.setBirthday(informationModel.getBirthday());
		user.setSubscribe(informationModel.getNews());
		user.setSex(informationModel.getGender());
		// Mật khẩu mặc định là 1234567.
		user.setPassword("1234567");
		user.setCreateday(timestamp.toString());

		userRepository.save(user);

		return informationModel;
	}

	
	/*
	 *  Cập nhật thông tin người dùng và trả về đối tượng đã cập nhật.
	 */
	@Override
	public InformationModel update(InformationModel informationModel) {
		// Lấy thông tin tên đăng nhập của người dùng hiện tại trong ứng dụng Spring Security.
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		// Tiến hành cập nhật thông tin username.
		User user = userRepository.findUserByEmail(username);
		user.setFullname(informationModel.getFullName());
		user.setBirthday(informationModel.getBirthday());
		user.setSubscribe(informationModel.getNews());
		user.setSex(informationModel.getGender());
		userRepository.save(user);

		return informationModel;
	}

	
	/*
	 *  Trả về thông tin người dùng dựa trên ID.
	 */
	@Override
	public InformationModel getOneUserById(Integer id) {
		User user = userRepository.findById(id).get();
		InformationModel information = new InformationModel();
		information.setFullName(user.getFullname());
		information.setEmail(user.getEmail());
		information.setGender(user.getSex());
		information.setNews(user.getSubscribe());
		information.setBirthday(user.getBirthday());

		return information;
	}

	
	/*
	 *  Cập nhật thông tin người dùng dựa trên ID và thông tin mới.
	 */
	@Override
	public InformationModel updateUser(InformationModel informationModel, Integer id) {
		// Tiến hành cập nhật user.
		User user = userRepository.findById(id).get();
		user.setFullname(informationModel.getFullName());
		user.setBirthday(informationModel.getBirthday());
		user.setSubscribe(informationModel.getNews());
		user.setSex(informationModel.getGender());
		userRepository.save(user);

		return informationModel;
	}

	
	/*
	 *  Cập nhật mật khẩu người dùng và trả về mô hình thay đổi mật khẩu.
	 */
	@Override
	public ChangePassModel updatePass(ChangePassModel changePassModel) {
		// Lấy thông tin tên đăng nhập của người dùng hiện tại trong ứng dụng Spring Security.
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		// Tiến hành update lại cái password.
		User user = userRepository.findUserByEmail(username);
		user.setPassword(changePassModel.getNewPass());
		userRepository.save(user);
		
		return changePassModel;
	}

	
	/*
	 *  Lưu hoặc cập nhật thông tin người dùng.
	 */
	@Override
	public void save(User user) {
		userRepository.save(user);
	}

	
	/*
	 *  Xóa người dùng dựa trên ID.
	 */
	@Override
	public void deleteUser(Integer id) {
		// Lấy thông tin tên đăng nhập của người dùng hiện tại trong ứng dụng Spring Security.
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		
		// Tạo một đối tượng Timestamp với thời gian hiện tại, được sử dụng để ghi lại thời gian xóa người dùng.
		User temp = userRepository.findUserByEmail(username);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		// Tiến hành xóa tạm thời chúng đi nhưng phải lưu lại trên csdll.
		User user = userRepository.findById(id).get();
		user.setDeleteday(timestamp.toString());
		user.setPersondelete(temp.getId());
		userRepository.save(user);
	}

}
