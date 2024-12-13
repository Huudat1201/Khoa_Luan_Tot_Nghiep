package com.KLTN.Service.Impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.KLTN.Entity.Comment;
import com.KLTN.Entity.Product;
import com.KLTN.Entity.User;
import com.KLTN.Model.CommentModel;
import com.KLTN.Repository.CommentRepository;
import com.KLTN.Repository.ProductRepository;
import com.KLTN.Repository.UserRepository;
import com.KLTN.Service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	
	/*
	 *  Lấy tất cả comment của một sản phẩm được truyền vào bởi id.
	 */
	@Override
	public List<Comment> getListCommentByProductId(Integer id) {
		return commentRepository.getListCommentByProductId(id);
	}
	

	/*
	 *  Danh sách các bình luận đang chờ xử lý.
	 */
	@Override
	public List<Comment> getListCommentPending() {
		return commentRepository.getListCommentPending();
	}

	
	/*
	 *  Danh sách các bình luận đã được kiểm tra. 
	 */
	@Override
	public List<Comment> getListCommentChecked() {
		return commentRepository.getListCommentChecked();
	}

	
	/*
	 *  Tạo mới một comment theo tham số commentModel được truyền vào.
	 */
	@Override
	public CommentModel createComment(CommentModel commentModel) {
		// Lấy thông tin tên đăng nhập của người dùng hiện tại trong ứng dụng Spring Security.
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		// Lấy cái username hiện tại đang đăng nhập.
		User temp = userRepository.findUserByEmail(username);

		// Sản phẩm đang được người dùng comment.
		Product product = productRepository.findById(commentModel.getProductId()).get();

		// Thời gian comment.
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = formatter.format(date);

		// Tiến hành tạo mới comment.
		Comment comment = new Comment();
		comment.setContent(commentModel.getContent());
		comment.setStar(commentModel.getStar());
		comment.setDate(strDate);
		comment.setProduct(product);
		comment.setUser(temp);
		comment.setStatus("0");
		commentRepository.save(comment);
		return commentModel;
	}

	
	/*
	 *  Lấy ra một bình luận theo cái id được truyền vào.
	 */
	@Override
	public Comment getCommentByCommentId(Integer id) {
		return commentRepository.getCommentByCommentId(id);
	}

	/*
	 *  Phê duyệt một bình luận dựa trên cái id được cung cấp.
	 */
	@Override
	public void approveComment(Integer id) {
		Comment comment = commentRepository.findById(id).get();
		// Set nó về cái status = 1 để được phê duyệt.
		comment.setStatus("1");
		commentRepository.save(comment);
	}

	
	/*
	 *  Đếm số lượng bình luận dựa trên tên sản phẩm được tìm kiếm. 
	 */
	@Override
	public int getCountCommentByProductNameSearch(String nameSearch) {
		return commentRepository.getCountCommentByProductNameSearch(nameSearch);
	}

	
	/*
	 *  Tính tổng số sao dựa trên tên sản phẩm được tìm kiếm.
	 */
	@Override
	public int getAllStarCommentByProductNameSearch(String nameSearch) {
		// Biến dùng để lưu trữ kết quả trung bình số sao.
		int result = 0;
		
		// Biến dùng để lưu tổng số sao từ tất cả các bình luận.
		int totalStar = 0;
		
		// Lấy danh sách số sao (danh sách các số nguyên) của các bình luận cho sản phẩm có tên tìm kiếm
		// nameSearch từ cơ sở dữ liệu thông qua commentRepository.
		List<Integer> listStar = commentRepository.getAllStarCommentByProductNameSearch(nameSearch);
		
		//  Kiểm tra nếu danh sách số sao không rỗng (tức là có ít nhất một bình luận).
		if (listStar.isEmpty() == false) {
			// Vòng lặp này duyệt qua từng phần tử trong danh sách listStar 
			// và cộng dồn các giá trị số sao vào biến totalStar.
			for (int i = 0; i < listStar.size(); i++) {
				totalStar = totalStar + listStar.get(i);
				System.out.println(totalStar);
			}
			// Tính trung bình số sao và làm tròn kết quả.
			result = Math.round(totalStar / (listStar.size()));
		}
		return result;
	}

	
	/*
	 *  Xóa comment theo id được truyền vào.
	 */
	@Override
	public void delete(Integer id) {
		Comment comment = commentRepository.findById(id).get();
		commentRepository.delete(comment);
	}

}
