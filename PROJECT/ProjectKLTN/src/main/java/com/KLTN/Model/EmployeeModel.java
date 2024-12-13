package com.KLTN.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeModel {
	
	// Họ và tên của nhân viên (Employee's full name)
    @Id
    String Fullname;

    // Phòng ban mà nhân viên làm việc (Department where the employee works)
    String department;

    // Số điện thoại của nhân viên (Employee's phone number)
    String phone;

    // Vị trí công việc của nhân viên (Employee's position)
    String position;

    // Ngày sinh của nhân viên (Employee's birthday)
    String birthday;

    // Ngày bắt đầu làm việc của nhân viên (Employee's start date)
    String Startday;

    // Mức lương của nhân viên (Employee's salary)
    int salary;
	
}
