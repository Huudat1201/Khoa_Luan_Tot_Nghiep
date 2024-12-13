package com.KLTN.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.KLTN.Entity.Role;
import com.KLTN.Entity.UserRole;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	// Truy vấn thông tin vai trò của người dùng dựa trên ID người dùng khớp với tham số uid
	// Kết quả trả về là một đối tượng UserRole chứa thông tin về vai trò của người dùng tương ứng.
	@Query("SELECT u FROM UserRole u WHERE u.user.id = :uid")
	UserRole getRoleByUserId(@Param("uid") Integer id);
	
	// Truy vấn danh sách tên vai trò của người dùng dựa trên ID người dùng khớp với tham số uid
	// Kết quả trả về là một danh sách các chuỗi (String) chứa tên vai trò của người dùng tương ứng.
	@Query("SELECT u.role.name FROM UserRole u WHERE u.user.id = :uid")
	List<String> getRoleNames(@Param("uid") Integer id);
	
}
