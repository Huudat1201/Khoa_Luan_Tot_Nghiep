package com.KLTN.Service;

import com.KLTN.Model.EmployeeForm;

public interface GeneralService {
	
	// Tạo một đối tượng nhân viên (EmployeeForm) mới.
	EmployeeForm createEmployee(EmployeeForm employee);
	
	// Trả về thông tin của một nhân viên dựa trên ID được cung cấp.
	EmployeeForm getOneUserById(Integer id);
	
	// Cập nhật thông tin của một nhân viên.
	EmployeeForm updateEmployee(EmployeeForm employeeForm);

}
