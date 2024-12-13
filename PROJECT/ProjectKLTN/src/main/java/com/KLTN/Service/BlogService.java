package com.KLTN.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.KLTN.Entity.Blog;
import com.KLTN.Model.BlogModel;

public interface BlogService {

	// Danh sách tất cả các bài viết.
	List<Blog> findAll();

	// Danh sách 6 bài viết được hiển trị trong trang chủ menu.
	List<Blog> getSixBlog();

	// Trả về một trang chứa các bài viết blog đang hoạt động, sử dụng thông tin phân trang từ đối tượng pageable.
	Page<Blog> findAllBlogActive(Pageable pageable);
	
	// Trả về một đối tượng bài viết blog mới dựa trên thông tin đầu vào là blogModel.
	BlogModel createBlog(BlogModel blogModel);
	
	// Trả về một bài viết dựa trên ID được cung cấp.
	BlogModel getOneBlogById(Integer id);
	
	// Tìm bài viết dựa trên ID được cung cấp.
	Blog findById(Integer id);

	// Tìm bài viết dựa trên tên tìm kiếm (nameSearch).
	Blog findBlogByNameSearch(String nameSearch);
	
	// Cập nhật bài viết
	BlogModel updateCategory(BlogModel blogModel);
	
	// Xóa một bài viết theo ID được cung cấp. 
	void delete(Integer id);

}
