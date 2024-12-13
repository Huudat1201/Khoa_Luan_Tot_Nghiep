package com.KLTN.Service.Impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.KLTN.Entity.Blog;
import com.KLTN.Entity.User;
import com.KLTN.Model.BlogModel;
import com.KLTN.Repository.BlogRepository;
import com.KLTN.Repository.UserRepository;
import com.KLTN.Service.BlogService;

@Service
public class BlogServiceImpl implements BlogService {
	
	@Autowired
	private BlogRepository blogRepository;

	@Autowired
	private UserRepository userRepository;

	
	/*
	 *  Danh sách tất cả các bài viết.
	 */
	@Override
	public List<Blog> findAll() {
		return blogRepository.getListBlog();
	}

	
	/*
	 *  Danh sách 6 bài viết được hiển trị trong trang chủ menu.
	 */
	@Override
	public List<Blog> getSixBlog() {
		List<Blog> listBlog = blogRepository.getSixBlogs();
		return listBlog;
	}

	
	/*
	 *  Trả về một trang chứa các bài viết blog đang hoạt động, sử dụng thông tin phân trang từ đối tượng pageable.
	 */
	@Override
	public Page<Blog> findAllBlogActive(Pageable pageable) {
		Page<Blog> listBlog = blogRepository.findAllBlogActive(pageable);
		return listBlog;
	}

	
	/*
	 *  Trả về một đối tượng bài viết blog mới dựa trên thông tin đầu vào là blogModel.
	 */
	@Override
	public BlogModel createBlog(BlogModel blogModel) {
		// Lấy đối tượng người dùng đang đăng nhập từ Security Context.
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		// Tạo một đối tượng Timestamp với thời gian hiện tại, được sử dụng để ghi lại thời gian tạo mới bài viết.
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User temp = userRepository.findUserByEmail(username);

		// Và tiến hành tạo mới Blog.
		Blog blog = new Blog();
		blog.setTitle(blogModel.getTitle());
		blog.setLogo(blogModel.getLogo());
		blog.setContent(blogModel.getContent());
		blog.setBanner(blogModel.getBanner());
		blog.setUploadday(blogModel.getUploadDay());
		blog.setPersoncreate(temp.getId());
		blog.setCreateday(timestamp.toString());
		blog.setActive(blogModel.isActive());
		blog.setDescription(blogModel.getDescription());
		blog.setNamesearch(blogModel.getNameSearch());
		blogRepository.save(blog);
		return blogModel;
	}

	
	/*
	 *  Trả về một bài viết dựa trên ID được cung cấp.
	 */
	@Override
	public BlogModel getOneBlogById(Integer id) {
		Blog blog = blogRepository.findById(id).get();
		BlogModel blogModel = new BlogModel();
		blogModel.setTitle(blog.getTitle());
		blogModel.setLogo(blog.getLogo());
		blogModel.setContent(blog.getContent());
		blogModel.setBanner(blog.getBanner());
		blogModel.setUploadDay(blog.getUploadday());
		blogModel.setDescription(blog.getDescription());
		blogModel.setNameSearch(blog.getNamesearch());
		blog.setActive(blogModel.isActive());
		return blogModel;
	}

	
	/*
	 *  Tìm bài viết dựa trên ID được cung cấp.
	 */
	@Override
	public Blog findById(Integer id) {
		return blogRepository.findById(id).get();
	}

	
	/*
	 *  Tìm bài viết dựa trên tên tìm kiếm (nameSearch).
	 */
	@Override
	public Blog findBlogByNameSearch(String nameSearch) {
		Blog blog = blogRepository.findBlogByNameSearch(nameSearch);
		return blog;
	}

	
	/*
	 *  Cập nhật bài viết
	 */
	@Override
	public BlogModel updateCategory(BlogModel blogModel) {
		// Lấy đối tượng người dùng đang đăng nhập từ Security Context.
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		// Tạo một đối tượng Timestamp với thời gian hiện tại, được sử dụng để ghi lại thời gian cập nhật bài viết.
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User temp = userRepository.findUserByEmail(username);

		// Và tiến hành cập nhật Blog.
		Blog blog = blogRepository.findById(blogModel.getId()).get();
		blog.setTitle(blogModel.getTitle());
		blog.setLogo(blogModel.getLogo());
		blog.setContent(blogModel.getContent());
		blog.setBanner(blogModel.getBanner());
		blog.setUploadday(blogModel.getUploadDay());
		blog.setUpdateday(timestamp.toString());
		blog.setPersonupdate(temp.getId());
		blog.setActive(blogModel.isActive());
		blog.setDescription(blogModel.getDescription());
		blog.setNamesearch(blogModel.getNameSearch());
		blogRepository.save(blog);
		return blogModel;
	}

	
	/*
	 *  Xóa một bài viết theo ID được cung cấp.
	 */
	@Override
	public void delete(Integer id) {
		// Lấy đối tượng người dùng đang đăng nhập từ Security Context xem ai là người xóa.
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		
		// Tạo một đối tượng Timestamp với thời gian hiện tại, được sử dụng để ghi lại thời gian cập nhật bài viết.
		User temp = userRepository.findUserByEmail(username);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		// Và tiến hành cho deleday có giá trị là timestamp truyền vào để người dùng không thấy bài viết này 
		// nhưng bài viết vẫn lưu lại trên cơ sở dữ liệu.
		Blog blog = blogRepository.findById(id).get();
		blog.setPersondelete(temp.getId());
		blog.setDeleteday(timestamp.toString());
		blogRepository.save(blog);
	}

}
