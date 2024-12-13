package com.KLTN.Model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeForm {
	
	// Mã định danh của nhân viên (Unique identifier for the employee)
    int id;

    // Email của nhân viên (Employee's email)
    String email;

    // Họ và tên của nhân viên (Employee's full name)
    String fullname;

    // Vị trí của nhân viên (Employee's position)
    String position;

    // Phòng ban mà nhân viên làm việc (Department where the employee works)
    String department;

    // Số điện thoại của nhân viên (Employee's phone number)
    String phone;

    // Mức lương của nhân viên (Employee's salary)
    int salary;

    // Ngày bắt đầu làm việc của nhân viên (Employee's start date)
    String startday;

    // Vai trò của nhân viên (Role assigned to the employee, often refers to user roles like admin, manager, etc.)
    int role;

}
