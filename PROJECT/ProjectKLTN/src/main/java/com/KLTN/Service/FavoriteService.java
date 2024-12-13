package com.KLTN.Service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.KLTN.Entity.Favorite;
import com.KLTN.Model.BestSellerModel;

public interface FavoriteService {
	
	// Danh sách các sản phẩm bán chạy nhất (BestSellerModel), giới hạn bởi thông tin phân trang (topFour).
	List<BestSellerModel> getListBestSellerProduct(Pageable topFour);
	
	// Danh sách các mục yêu thích (Favorite) dựa trên email của người dùng.
	List<Favorite> getListFavoriteByEmail();
	
	// Tạo và trả về một mục yêu thích (Favorite) mới dựa trên ID được cung cấp.
	Favorite create(int id);
	
	// Trả về một mục yêu thích (Favorite) dựa trên ID được cung cấp.
	Favorite getOneFavorite(int id);
	
	// Xóa một mục yêu thích dựa trên cái id được cung cấp.
	void delete(int id);

}
