package com.KLTN.Service;

import java.util.List;

import com.KLTN.Entity.Employee;
import com.KLTN.Model.EmployeeModel;

public interface EmployeeService {
	
	// Lấy danh sách tất cả nhân viên.
	List<EmployeeModel> getListEmployee();

	// Lưu đối tượng nhân viên xuống cơ sở dữ liệu.
	void save(Employee employee);
	
}
