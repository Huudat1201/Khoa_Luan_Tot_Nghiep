package com.KLTN.Repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.KLTN.Entity.Favorite;
import com.KLTN.Model.BestSellerModel;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer>{
	
	// Truy vấn danh sách các sản phẩm bán chạy (Best Seller) và số lượng yêu thích của chúng
	// Chỉ các sản phẩm chưa bị xóa (Deleteday = null) và đang hoạt động (active = 1) mới được truy vấn
	// Kết quả được nhóm theo sản phẩm và sắp xếp giảm dần theo số lượng yêu thích
	// new BestSellerModel(f.product, count(f.product)) 
	// là cách sử dụng constructor expression để tạo các đối tượng BestSellerModel.
	@Query("SELECT new BestSellerModel(f.product, count(f.product))"
			+ " FROM Favorite f WHERE f.product.Deleteday = null AND f.product.active = 1"
			+ " GROUP BY f.product ORDER BY count(f.product) DESC")
	List<BestSellerModel> getListBestSellerProduct(Pageable pageable);
	
	// Truy vấn tìm một Favorite mà địa chỉ email của người dùng khớp với tham số đầu vào (email)
	// và ID của sản phẩm khớp với tham số thứ hai (id)
	// ?1 đại diện cho tham số đầu tiên (email) và ?2 cho tham số thứ hai (id).
	@Query("SELECT f FROM Favorite f WHERE f.user.email LIKE ?1 and f.product.id = ?2")
	Favorite getOneFavorite(String email, int id);
	
	// Lấy tất cả các Favorite mà địa chỉ email của người dùng khớp với tham số đầu vào (email)
	// và chỉ lấy các sản phẩm mà trường Deleteday là NULL (chưa bị xóa)
	// Tóm lại là chỉ lấy các sản phẩm yêu thích còn khả dụng.
	@Query("SELECT f FROM Favorite f WHERE f.user.email LIKE ?1 and f.product.Deleteday = null")
	List<Favorite> getListFavoriteByEmail(String email);
	
}
