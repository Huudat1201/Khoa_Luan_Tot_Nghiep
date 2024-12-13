package com.KLTN.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.KLTN.Entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	// Đoạn query này tìm một Category có giá trị Namesearch khớp với tham số được
	// truyền vào và
	// Category chưa bị xóa được thiết lập null.
	@Query("SELECT c FROM Category c WHERE c.Deleteday = null AND c.Namesearch LIKE ?1")
	Category getCategoryByNameSearch(String nameSearch);

	// Truy vấn danh sách các đối tượng Category chưa bị xóa.
	@Query("SELECT c FROM Category c WHERE c.Deleteday = null")
	List<Category> getListCategory();

}
