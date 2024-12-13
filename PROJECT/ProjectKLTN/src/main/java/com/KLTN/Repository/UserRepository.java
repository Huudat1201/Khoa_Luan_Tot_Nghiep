package com.KLTN.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.KLTN.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	// Truy vấn tất cả người dùng chưa bị xóa.
	@Query("SELECT u FROM User u WHERE u.Deleteday = null")
	List<User> findAllUserAnable();
	
	// Truy vấn người dùng chưa bị xóa dựa trên địa chỉ email khớp với tham số uemail.
	@Query("SELECT u FROM User u WHERE u.email = :uemail and u.Deleteday = null")
	User findUserByEmail(@Param("uemail") String email);
	
	// Truy vấn tất cả người dùng có tài khoản đang hoạt động không có vai trò là Admin.
	@Query(value = "SELECT * FROM Users WHERE NOT EXISTS(SELECT * FROM Employees WHERE Users.Id = Employees.User_Id)"
			+ " AND DeleteDay is NULL", nativeQuery = true)
	List<User> findAllUserNotRoleAdmin();
	
	// Truy vấn danh sách người dùng có tài khoản đang hoạt động đã đăng ký nhận thông tin.
	@Query("SELECT u FROM User u WHERE u.Deleteday = null AND u.subscribe = 1")
	List<User> getListUserEnableSubscribe();

}
