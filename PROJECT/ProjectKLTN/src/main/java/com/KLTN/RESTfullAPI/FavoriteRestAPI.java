package com.KLTN.RESTfullAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.KLTN.Entity.Favorite;
import com.KLTN.Service.FavoriteService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/favorite")
public class FavoriteRestAPI {
	
	@Autowired
	private FavoriteService favoriteService;
	
	// Lấy một cái sản phẩm yêu thích theo sản phẩm.
	@GetMapping("/{id}")
	public Favorite getOneFavorite(@PathVariable("id") int id) {
		return favoriteService.getOneFavorite(id);
	}
	
	// Tạo mới một favorite theo sản phẩm.
	@PostMapping("/add/{id}")
	public Favorite create(@PathVariable("id") int id) {
		return favoriteService.create(id);
	}
	

}
