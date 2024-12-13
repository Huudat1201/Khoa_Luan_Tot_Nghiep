package com.KLTN.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KLTN.Entity.Employee;
import com.KLTN.Model.EmployeeModel;
import com.KLTN.Repository.EmployeeRepository;
import com.KLTN.Service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	/*
	 *  Lấy danh sách tất cả nhân viên.
	 */
	@Override
	public List<EmployeeModel> getListEmployee() {
		return employeeRepository.getListEmployee();
	}

	/*
	 *  Lưu đối tượng nhân viên xuống cơ sở dữ liệu.
	 */
	@Override
	public void save(Employee employee) {
		employeeRepository.save(employee);
	}

}
