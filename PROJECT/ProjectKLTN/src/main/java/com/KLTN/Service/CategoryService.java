package com.KLTN.Service;

import java.util.List;

import com.KLTN.Entity.Category;
import com.KLTN.Model.CategoryModel;

public interface CategoryService {

	// Lấy tất cả các danh mục.
	List<Category> findAll();
	
	// Lấy một danh mục theo id.
	CategoryModel getOneCategoryById(Integer id);
	
	// Lấy ra một danh mục theo cái namesearch được truyền vào.
	Category getCategoryByNameSearch(String nameSearch);
	
	// Tạo mới một danh mục theo đối tượng categoryModel được truyền vào.
	CategoryModel createCategory(CategoryModel categoryModel);
	
	// Cập nhật danh mục theo đối tượng categoryModel được truyền vào.
	CategoryModel updateCategory(CategoryModel categoryModel);
	
	// Xóa danh mục theo id.
	void delete(Integer id);

}
