package com.KLTN.Service;

import java.util.List;

import com.KLTN.Entity.Role;

public interface RoleService {
	
	// Danh sách tên các vai trò của người dùng dựa trên userId.
	List<String> getRoleNames(int userId);
	
	// Tìm và trả về vai trò (Role) dựa trên roleId.
	Role findRoleById(int roleId);

}
