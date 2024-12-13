package com.KLTN.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.KLTN.Entity.Employee;
import com.KLTN.Model.EmployeeModel;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	// Truy vấn một đối tượng Employee dựa trên ID của người dùng (user) khớp với tham số uid
	// :uid là một tham số được sử dụng để truyền vào giá trị của biến id.
	@Query("SELECT e FROM Employee e WHERE e.user.id = :uid")
	Employee getEmployeeByUserId(@Param("uid") Integer id);

	// Truy vấn danh sách các đối tượng EmployeeModel tùy chỉnh từ các trường của đối tượng Employee
	// Chỉ các Employee chưa bị xóa (Deleteday = null) mới được truy vấn
	// new EmployeeModel(...)
	// là cách sử dụng constructor expression để tạo các đối tượng EmployeeModel từ các thuộc tính của Employee.
	@Query("SELECT new EmployeeModel(e.user.Fullname, e.department, e.phone,"
			+ " e.position, e.user.birthday, e.Startday, e.salary)"
			+ " FROM Employee e WHERE e.Deleteday = null")
	List<EmployeeModel> getListEmployee();

}
