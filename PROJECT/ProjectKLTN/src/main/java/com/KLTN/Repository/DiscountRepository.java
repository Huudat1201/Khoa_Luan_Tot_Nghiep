package com.KLTN.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.KLTN.Entity.Discount;

public interface DiscountRepository extends JpaRepository<Discount, Integer> {
	
	// Truy vấn một đối tượng Discount dựa trên mã giảm giá (Code)
	// Nó tìm một Discount mà trường Code khớp với tham số truyền vào và trường Deleteday là NULL (chưa bị xóa)
	// ?1 đại diện cho tham số đầu tiên được truyền vào (code).
	@Query(value = "SELECT * FROM discount where deleteday is Null and Code LIKE ?1", nativeQuery = true)
	Discount getDiscountByCode(String code);
	
	// Truy vấn danh sách các đối tượng Discount còn khả dụng (chưa bị xóa).
	@Query(value = "SELECT * FROM discount where deleteday is Null;", nativeQuery = true)
	List<Discount> getListDiscountAvailable();
	
	// Truy vấn danh sách các đối tượng Discount chưa bị xóa.
	@Query("SELECT d FROM Discount d WHERE d.Deleteday = null")
	List<Discount> getListDiscount();

}
