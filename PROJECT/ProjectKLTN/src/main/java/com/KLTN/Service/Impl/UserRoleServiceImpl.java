package com.KLTN.Service.Impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.KLTN.Entity.Employee;
import com.KLTN.Entity.User;
import com.KLTN.Entity.UserRole;
import com.KLTN.Repository.EmployeeRepository;
import com.KLTN.Repository.UserRepository;
import com.KLTN.Repository.UserRoleRepository;
import com.KLTN.Service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmployeeRepository employeeRepository;
	
	/*
	 *  Trả về danh sách tất cả các vai trò của người dùng.
	 */
	@Override
	public List<UserRole> findAll() {
		return userRoleRepository.findAll();
	}

	/*
	 *  Trả về danh sách các vai trò là Admin hoặc Director.
	 */
	@Override
	public List<UserRole> findAllAdminOrDirector() {
		return userRoleRepository.findAllAdminOrDirector();
	}
	
	/*
	 *  Lưu hoặc cập nhật một vai trò của người dùng (UserRole).
	 */
	@Override
	public void save(UserRole userRole) {
		userRoleRepository.save(userRole);
	}
	
	/*
	 *  Xóa vai trò người dùng dựa trên ID.
	 */
	@Override
	public void delete(Integer id) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		User temp = userRepository.findUserByEmail(username);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User user = userRepository.findById(id).get();

		if (!temp.getEmail().equals(user.getEmail())) {
			user.setDeleteday(timestamp.toString());
			user.setPersondelete(temp.getId());
			userRepository.save(user);

			List<Employee> listEmployee = user.getListEmployee();
			for (Employee e : listEmployee) {
				e.setDeleteday(timestamp.toString());
				e.setPersondelete(temp.getId());
				employeeRepository.save(e);
			}
		}

		else {
			throw new RuntimeException();
		}
	}

}
