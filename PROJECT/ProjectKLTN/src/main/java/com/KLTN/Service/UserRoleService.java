package com.KLTN.Service;

import java.util.List;

import com.KLTN.Entity.UserRole;

public interface UserRoleService {
	
	// Trả về danh sách tất cả các vai trò của người dùng.
	List<UserRole> findAll();
	
	// Trả về danh sách các vai trò là Admin hoặc Director.
	List<UserRole> findAllAdminOrDirector();
	
	// Lưu hoặc cập nhật một vai trò của người dùng (UserRole).
	void save(UserRole userRole);
	
	// Xóa vai trò người dùng dựa trên ID.
	void delete(Integer id);

}
