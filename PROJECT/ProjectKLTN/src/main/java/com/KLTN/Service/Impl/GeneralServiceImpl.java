package com.KLTN.Service.Impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.KLTN.Entity.Employee;
import com.KLTN.Entity.Role;
import com.KLTN.Entity.User;
import com.KLTN.Entity.UserRole;
import com.KLTN.Model.EmployeeForm;
import com.KLTN.Repository.EmployeeRepository;
import com.KLTN.Repository.RoleRepository;
import com.KLTN.Repository.UserRepository;
import com.KLTN.Repository.UserRoleRepository;
import com.KLTN.Service.GeneralService;

@Service
public class GeneralServiceImpl implements GeneralService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRoleRepository userroleRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	/*
	 *  Tạo một đối tượng nhân viên (EmployeeForm) mới.
	 */
	@Override
	public EmployeeForm createEmployee(EmployeeForm employeeForm) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User user = new User();
		user.setEmail(employeeForm.getEmail());
		user.setPassword("1234567");
		user.setFullname(employeeForm.getFullname());
		user.setCreateday(timestamp.toString());
		userRepository.save(user);

		Role role = roleRepository.findById(employeeForm.getRole()).get();

		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		userroleRepository.save(userRole);

		// Lấy thông tin tên đăng nhập của người dùng hiện tại trong ứng dụng Spring
		// Security.
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		User temp = userRepository.findUserByEmail(username);

		Employee employee = new Employee();
		employee.setDepartment(employeeForm.getDepartment());
		employee.setPosition(employeeForm.getPosition());
		employee.setPhone(employeeForm.getPhone());
		employee.setStartday(employeeForm.getStartday());
		employee.setSalary(employeeForm.getSalary());
		employee.setCreateday(timestamp.toString());
		employee.setUser(user);
		employee.setPersoncreate(temp.getId());
		employeeRepository.save(employee);

		return employeeForm;
	}

	/*
	 *  Trả về thông tin của một nhân viên dựa trên ID được cung cấp.
	 */
	@Override
	public EmployeeForm getOneUserById(Integer id) {
		User user = userRepository.findById(id).get();
		EmployeeForm employeeForm = new EmployeeForm();
		employeeForm.setFullname(user.getFullname());
		employeeForm.setEmail(user.getEmail());

		for (Employee employee : user.getListEmployee()) {
			employeeForm.setDepartment(employee.getDepartment());
			employeeForm.setPhone(employee.getPhone());
			employeeForm.setSalary(employee.getSalary());
			employeeForm.setPosition(employee.getPosition());
			employeeForm.setStartday(employee.getStartday());
		}

		for (UserRole userRole : user.getListUserRole()) {
			employeeForm.setRole(userRole.getRole().getId());
		}

		return employeeForm;
	}

	/*
	 *  Cập nhật thông tin của một nhân viên.
	 */
	@Override
	public EmployeeForm updateEmployee(EmployeeForm employeeForm) {
		User user = userRepository.findById(employeeForm.getId()).get();
		user.setEmail(employeeForm.getEmail());
		user.setFullname(employeeForm.getFullname());
		userRepository.save(user);

		UserRole userRole = roleRepository.getRoleByUserId(employeeForm.getId());
		Role role = roleRepository.findById(employeeForm.getRole()).get();
		userRole.setUser(user);
		userRole.setRole(role);
		userroleRepository.save(userRole);

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		User temp = userRepository.findUserByEmail(username);

		Employee employee = employeeRepository.getEmployeeByUserId(employeeForm.getId());
		employee.setDepartment(employeeForm.getDepartment());
		employee.setPosition(employeeForm.getPosition());
		employee.setPhone(employeeForm.getPhone());
		employee.setStartday(employeeForm.getStartday());
		employee.setSalary(employeeForm.getSalary());
		employee.setUpdateday(timestamp.toString());
		employee.setUser(user);
		employee.setPersonupdate(temp.getId());
		employeeRepository.save(employee);

		return employeeForm;
	}

}
