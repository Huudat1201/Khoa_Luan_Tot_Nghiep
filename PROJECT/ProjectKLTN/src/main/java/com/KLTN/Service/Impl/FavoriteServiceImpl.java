package com.KLTN.Service.Impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.KLTN.Entity.Favorite;
import com.KLTN.Entity.Product;
import com.KLTN.Entity.User;
import com.KLTN.Model.BestSellerModel;
import com.KLTN.Repository.FavoriteRepository;
import com.KLTN.Repository.ProductRepository;
import com.KLTN.Repository.UserRepository;
import com.KLTN.Service.FavoriteService;

@Service
public class FavoriteServiceImpl implements FavoriteService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private FavoriteRepository favoriteRepository;

	@Autowired
	private UserRepository userRepository;

	/*
	 * Danh sách các sản phẩm bán chạy nhất (BestSellerModel), giới hạn bởi thông
	 * tin phân trang (topFour).
	 */
	@Override
	public List<BestSellerModel> getListBestSellerProduct(Pageable topFour) {
		return favoriteRepository.getListBestSellerProduct(topFour);
	}

	/*
	 * Danh sách các mục yêu thích (Favorite) dựa trên email của người dùng.
	 */
	@Override
	public List<Favorite> getListFavoriteByEmail() {
		// Lấy thông tin tên đăng nhập của người dùng hiện tại trong ứng dụng Spring
		// Security.
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		return favoriteRepository.getListFavoriteByEmail(username);
	}

	/*
	 * Tạo và trả về một mục yêu thích (Favorite) mới dựa trên ID được cung cấp.
	 */
	@Override
	public Favorite create(int id) {
		// Lấy thông tin tên đăng nhập của người dùng hiện tại trong ứng dụng Spring
		// Security.
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		User temp = userRepository.findUserByEmail(username);

		// => Tiến hành tạo mới favorite yêu thích sản phẩm.
		Favorite favorite = new Favorite();
		// Nếu tìm thấy người dùng thì xử lý logic:
		if (temp != null) {
			// Lấy cái favorite theo người dùng và cái id sản phẩm yêu thích.
			favorite = favoriteRepository.getOneFavorite(username, id);
			// nếu như cái favorite là null thì tiến hành tạo mới.
			if (favorite == null) {
				favorite = new Favorite();
				Date date = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String strDate = formatter.format(date);
				Product product = productRepository.findById(id).get();
				favorite.setProduct(product);
				favorite.setUser(temp);
				favorite.setDate(strDate);
				favoriteRepository.save(favorite);
			}
			// Còn không thì tiến hành xóa cái favorite này.
			else {
				favoriteRepository.delete(favorite);
				favorite.setId(0);
			}

		}
		return favorite;
	}

	/*
	 * Trả về một mục yêu thích (Favorite) dựa trên ID được cung cấp.
	 */
	@Override
	public Favorite getOneFavorite(int id) {
		
		Favorite favorite = new Favorite();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";

		try {
			username = ((UserDetails) principal).getUsername();
		} catch (Exception e) {

		}
		
		User temp = userRepository.findUserByEmail(username);

		if (temp != null) {
			favorite = favoriteRepository.getOneFavorite(username, id);
		} else {
			favorite = null;
		}
		
		return favorite;
	}

	
	/*
	 * Xóa một mục yêu thích dựa trên cái id được cung cấp.
	 */
	@Override
	public void delete(int id) {
		Favorite favorite = favoriteRepository.findById(id).get();
		favoriteRepository.delete(favorite);
	}

}
