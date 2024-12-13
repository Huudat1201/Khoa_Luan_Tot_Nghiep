package com.KLTN.RESTfullAPI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.KLTN.Entity.UserRole;
import com.KLTN.Service.UserRoleService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/employees")
public class EmployeeRestAPI {
	
	@Autowired
	UserRoleService userRoleService;
	
	// Lấy danh sách vai trò người dùng.
	@GetMapping()
	public List<UserRole> getAll() {
		return userRoleService.findAllAdminOrDirector();
	}
	
	// Xóa một nhân viên.
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		userRoleService.delete(id);
	}
	
}
