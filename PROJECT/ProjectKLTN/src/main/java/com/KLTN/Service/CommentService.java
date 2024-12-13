package com.KLTN.Service;

import java.util.List;

import com.KLTN.Entity.Comment;
import com.KLTN.Model.CommentModel;

public interface CommentService {
	
	// Lấy tất cả comment của một sản phẩm được truyền vào bởi id.
	List<Comment> getListCommentByProductId(Integer id);
	
	// Danh sách các bình luận đang chờ xử lý.
	List<Comment> getListCommentPending();
	
	// Danh sách các bình luận đã được kiểm tra. 
	List<Comment> getListCommentChecked();

	// Tạo mới một comment theo tham số commentModel được truyền vào.
	CommentModel createComment(CommentModel commentModel);

	// Lấy ra một bình luận theo cái id được truyền vào.
	Comment getCommentByCommentId(Integer id);

	// Phê duyệt một bình luận dựa trên cái id được cung cấp.
	void approveComment(Integer id);

	// Đếm số lượng bình luận dựa trên tên sản phẩm được tìm kiếm. 
	int getCountCommentByProductNameSearch(String nameSearch);
	
	// Tính tổng số sao dựa trên tên sản phẩm được tìm kiếm.
	int getAllStarCommentByProductNameSearch(String nameSearch);
	
	// Xóa comment theo id được truyền vào. 
	void delete(Integer id);

}
