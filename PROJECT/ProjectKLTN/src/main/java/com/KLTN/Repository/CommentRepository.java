package com.KLTN.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.KLTN.Entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	
	// Ttruy vấn danh sách các sao (star) từ các bình luận của sản phẩm dựa trên tên tìm kiếm của sản phẩm
	// (Namesearch) khớp với tham số unameSearch
	// và có trạng thái bình luận là 1 (c.status = 1).
	@Query("SELECT c.star FROM Comment c WHERE c.product.Namesearch = :unameSearch AND c.status = 1")
	List<Integer> getAllStarCommentByProductNameSearch(@Param("unameSearch") String nameSearch);
	
	// Truy vấn một Comment có giá trị ID khớp với tham số uid.
	@Query("SELECT c FROM Comment c WHERE c.id = :uid")
	Comment getCommentByCommentId(@Param("uid") Integer id);
	
	// Lấy tất cả các bình luận có status bằng 1 và thuộc về sản phẩm có ID khớp với tham số uid.
	@Query("SELECT c FROM Comment c WHERE c.status = 1 AND c.product.id = :uid")
	List<Comment> getListCommentByProductId(@Param("uid") Integer id);
	
	// Đếm tất cả các bình luận có giá trị Namesearch của sản phẩm khớp với tham số unameSearch
	// và có trạng thái bình luận là 1 (c.status = 1).
	@Query("SELECT COUNT(c) FROM Comment c WHERE c.product.Namesearch = :unameSearch AND c.status = 1")
	int getCountCommentByProductNameSearch(@Param("unameSearch") String nameSearch);
	
	// Đoạn query này lấy tất cả các bình luận có trạng thái là 0 (đang chờ xử lý).
	@Query("SELECT c FROM Comment c WHERE c.status = 0")
	List<Comment> getListCommentPending();
	
	// Đoạn query này lấy tất cả các bình luận có trạng thái là 1 (đã được duyệt).
	@Query("SELECT c FROM Comment c WHERE c.status = 1")
	List<Comment> getListCommentChecked();

}
