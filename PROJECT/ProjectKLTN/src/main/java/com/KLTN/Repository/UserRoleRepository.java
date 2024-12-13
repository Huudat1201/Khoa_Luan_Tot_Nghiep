package com.KLTN.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.KLTN.Entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer>{
	
	// Truy vấn tất cả các vai trò người dùng chưa bị xóa có ID vai trò là 2 (Admin) hoặc 3 (Director).
	@Query("SELECT u FROM UserRole u WHERE (u.role.id = 2 or u.role.id = 3) and u.user.Deleteday = null")
	List<UserRole> findAllAdminOrDirector();
	
}
 