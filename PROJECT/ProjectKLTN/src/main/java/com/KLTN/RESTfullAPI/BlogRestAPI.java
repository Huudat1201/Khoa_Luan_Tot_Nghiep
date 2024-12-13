package com.KLTN.RESTfullAPI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.KLTN.Entity.Blog;
import com.KLTN.Model.BlogModel;
import com.KLTN.Service.BlogService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/blog")
public class BlogRestAPI {

	@Autowired
	private BlogService blogService;

	// Lấy danh sách các blog.
	@GetMapping()
	public List<Blog> getAll() {
		return blogService.findAll();
	}

	// Lấy một blog theo cái id truyền vào.
	@GetMapping("/form/{id}")
	public BlogModel getOneUserById(@PathVariable("id") Integer id) {
		return blogService.getOneBlogById(id);
	}

	// Thêm mới một blog.
	@PostMapping("/form")
	public BlogModel create(@RequestBody BlogModel blogModel) {
		return blogService.createBlog(blogModel);
	}

	// Cập nhật blog.
	@PutMapping("/form/{id}")
	public BlogModel update(@PathVariable("id") Integer id, @RequestBody BlogModel blogModel) {
		return blogService.updateCategory(blogModel);
	}

	// Xóa một blog.
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		blogService.delete(id);
	}

}
