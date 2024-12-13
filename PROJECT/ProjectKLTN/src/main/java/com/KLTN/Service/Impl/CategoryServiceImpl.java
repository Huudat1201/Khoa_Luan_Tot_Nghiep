package com.KLTN.Service.Impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.KLTN.Entity.Category;
import com.KLTN.Entity.User;
import com.KLTN.Model.CategoryModel;
import com.KLTN.Repository.CategoryRepository;
import com.KLTN.Repository.UserRepository;
import com.KLTN.Service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	/*
	 *  Lấy tất cả các danh mục.
	 */
	@Override
	public List<Category> findAll() {
		return categoryRepository.getListCategory();
	}
	
	/*
	 *  Lấy một danh mục theo id.
	 */
	@Override
	public CategoryModel getOneCategoryById(Integer id) {
		Category category = categoryRepository.findById(id).get();
		CategoryModel categoryModel = new CategoryModel();
		categoryModel.setName(category.getName());
		categoryModel.setNameSearch(category.getNamesearch());
		categoryModel.setLogo(category.getLogo());
		categoryModel.setBanner(category.getBanner());
		categoryModel.setDescribe(category.getDescription());
		return categoryModel;
	}
	
	/*
	 *  Lấy ra một danh mục theo cái namesearch được truyền vào.
	 */
	@Override
	public Category getCategoryByNameSearch(String nameSearch) {
		return categoryRepository.getCategoryByNameSearch(nameSearch);
	}
	
	/*
	 *  Tạo mới một danh mục theo đối tượng categoryModel được truyền vào.
	 */
	@Override
	public CategoryModel createCategory(CategoryModel categoryModel) {
		// Lấy thông tin tên đăng nhập của người dùng hiện tại trong ứng dụng Spring Security.
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		
		// Tạo một đối tượng Timestamp với thời gian hiện tại, được sử dụng để ghi lại thời gian tạo mới danh mục.
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User temp = userRepository.findUserByEmail(username);
		
		// Tiến hành tạo mới danh mục.
		Category category = new Category();
		category.setName(categoryModel.getName());
		category.setNamesearch(categoryModel.getNameSearch());
		category.setLogo(categoryModel.getLogo());
		category.setBanner(categoryModel.getBanner());
		category.setDescription(categoryModel.getDescribe());
		category.setPersoncreate(temp.getId());
		category.setCreateday(timestamp.toString());
		categoryRepository.save(category);
		return categoryModel;
	}

	/*
	 *  Cập nhật danh mục theo đối tượng categoryModel được truyền vào.
	 */
	@Override
	public CategoryModel updateCategory(CategoryModel categoryModel) {
		// Lấy thông tin tên đăng nhập của người dùng hiện tại trong ứng dụng Spring Security.
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		
		// Tạo một đối tượng Timestamp với thời gian hiện tại, được sử dụng để ghi lại thời gian cập nhật danh mục.
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User temp = userRepository.findUserByEmail(username);
		
		// Tiến hành cập nhật danh mục.
		Category category = categoryRepository.findById(categoryModel.getId()).get();
		category.setName(categoryModel.getName());
		category.setNamesearch(categoryModel.getNameSearch());
		category.setLogo(categoryModel.getLogo());
		category.setBanner(categoryModel.getBanner());
		category.setDescription(categoryModel.getDescribe());
		category.setUpdateday(timestamp.toString());
		category.setPersonupdate(temp.getId());
		categoryRepository.save(category);
		return categoryModel;
	}
	
	/*
	 *  Xóa danh mục theo id.
	 */
	@Override
	public void delete(Integer id) {
		// Lấy thông tin tên đăng nhập của người dùng hiện tại trong ứng dụng Spring Security.
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		
		// Tạo một đối tượng Timestamp với thời gian hiện tại, được sử dụng để ghi lại thời gian xóa danh mục.
		User temp = userRepository.findUserByEmail(username);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		// Không xóa danh mục hoàn toàn mà lưu lại trên csdl để đối chiếu.
		Category category = categoryRepository.findById(id).get();
		category.setPersondelete(temp.getId());
		category.setDeleteday(timestamp.toString());
		categoryRepository.save(category);
	}
	
}
