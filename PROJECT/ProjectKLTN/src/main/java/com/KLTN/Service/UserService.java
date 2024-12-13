package com.KLTN.Service;

import java.util.List;

import com.KLTN.Entity.User;
import com.KLTN.Model.ChangePassModel;
import com.KLTN.Model.InformationModel;

public interface UserService {

	// Danh sách tất cả người dùng.
	List<User> findAll();

	// Danh sách người dùng không có vai trò Admin.
	List<User> findAllUserNotRoleAdmin();

	// Danh sách tất cả người dùng đã kích hoạt tài khoản.
	List<User> findAllUserAnable();
	
	// Danh sách người dùng đã đăng ký nhận thông báo.
	List<User> getListUserEnableSubscribe();

	// Lưu một người dùng mới.
	User create(User user);

	// Trả về người dùng dựa trên ID.
	User findById(Integer id);
	
	// Trả về người dùng dựa trên email.
	User findUserByEmail(String email);
	
	// Trả về thông tin tài khoản người dùng hiện tại.
	InformationModel getUserAccount();
	
	// Tạo một người dùng mới và trả về thông tin người dùng đã tạo.
	InformationModel createUser(InformationModel informationModel);

	// Cập nhật thông tin người dùng và trả về đối tượng đã cập nhật.
	InformationModel update(InformationModel informationModel);

	// Trả về thông tin người dùng dựa trên ID.
	InformationModel getOneUserById(Integer id);

	// Cập nhật thông tin người dùng dựa trên ID và thông tin mới.
	InformationModel updateUser(InformationModel informationModel, Integer id);

	// Cập nhật mật khẩu người dùng và trả về mô hình thay đổi mật khẩu.
	ChangePassModel updatePass(ChangePassModel changePassModel);

	// Lưu hoặc cập nhật thông tin người dùng.
	void save(User user);
	
	// Xóa người dùng dựa trên ID.
	void deleteUser(Integer id);

}
