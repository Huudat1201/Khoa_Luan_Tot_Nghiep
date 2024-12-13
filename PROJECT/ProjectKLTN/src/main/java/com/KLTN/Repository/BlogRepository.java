package com.KLTN.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.KLTN.Entity.Blog;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
	
	// Truy vấn một đối tượng Blog dựa trên giá trị của trường Namesearch khớp với tham số được truyền vào.
	@Query("SELECT c FROM Blog c WHERE c.Namesearch = ?1")
	Blog findBlogByNameSearch(String nameSearch);
	
	// Lấy 6 bài viết (Blog) gần nhất, chưa bị xóa (deleteDay = NULL) và đang ở trạng thái hoạt động (Active = 1).
	@Query(value="SELECT * FROM Blogs WHERE Active = 1 AND deleteDay IS NULL ORDER BY UploadDay DESC LIMIT 6",
			nativeQuery = true)
	List<Blog> getSixBlogs();
	
	// Truy vấn các đối tượng Blog chưa bị xóa và đang ở trạng thái hoạt động active = 1
	// Pageable được sử dụng để hỗ trợ phân trang cho kết quả truy vấn.
	@Query("SELECT c FROM Blog c WHERE c.Deleteday = null AND c.active = 1")
	Page<Blog> findAllBlogActive(Pageable pageable);
	
	// Lấy tất cả các bài viết chưa bị xóa.
	@Query("SELECT c FROM Blog c WHERE c.Deleteday = null")
	List<Blog> getListBlog();
}
