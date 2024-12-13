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

import com.KLTN.Entity.Comment;
import com.KLTN.Model.CommentModel;
import com.KLTN.Service.CommentService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/comment")
public class CommentRestAPI {
	
	@Autowired
	private CommentService commentService;
	
	// Lấy một bình luận theo cái id của sản phẩm.
	@GetMapping("/form/product/{id}")
	public List<Comment> getListCommentByProductId(@PathVariable("id") Integer id) {
		return commentService.getListCommentByProductId(id);
	}
	
	// Lấy tất cả các bình luận đang chờ xử lý (Chức năng dành cho admin).
	@GetMapping("/pending")
	public List<Comment> getListCommentPending(){
		return commentService.getListCommentPending();
	}
	
	// Lấy tất cả các danh sách bình luận đã kiểm duyệt.
	@GetMapping("/approved")
	public List<Comment> getListCommentChecked(){
		return commentService.getListCommentChecked();
	}
	
	// Lấy một bình luận.
	@GetMapping("/pending/{id}")
	public Comment getCommentByCommentId(@PathVariable("id") Integer id) {
		return commentService.getCommentByCommentId(id);
	}
	
	// Tạo mới bình luận.
	@PostMapping("/form")
	public CommentModel create(@RequestBody CommentModel commentModel) {
		return commentService.createComment(commentModel);
	}
	
	// Chỉnh sửa cái bình luận theo cái id truyền vào.
	@PutMapping("/form/approve/{id}")
	public void approve(@PathVariable("id") Integer id) {
		commentService.approveComment(id);
	}
	
	// Xóa một bình luận.
	@DeleteMapping("/form/delete/{id}")
	public void delete(@PathVariable("id") Integer id) {
		commentService.delete(id);
	}
	
}
