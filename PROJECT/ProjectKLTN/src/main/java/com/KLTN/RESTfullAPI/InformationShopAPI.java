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

import com.KLTN.Entity.InformationShop;
import com.KLTN.Model.ShopModel;
import com.KLTN.Service.InformationShopService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/shop")
public class InformationShopAPI {

	@Autowired
	private InformationShopService informationShopService;

	// Lấy danh sách các shop mà chuỗi cửa hàng đang bán.
	@GetMapping()
	public List<InformationShop> getAll() {
		return informationShopService.findAll();
	}

	// lấy một cửa hàng theo cái id truyền vào.
	@GetMapping("/form/{id}")
	public ShopModel getOneShopById(@PathVariable("id") Integer id) {
		return informationShopService.getOneShopById(id);
	}

	// Thêm mới một cửa hàng.
	@PostMapping("/form")
	public ShopModel create(@RequestBody ShopModel shopModel) {
		return informationShopService.createInformationShop(shopModel);
	}

	// update lại cửa hàng hoạt động bình thường.
	@PutMapping("/form/active/{id}")
	public ShopModel update(@PathVariable("id") Integer id, @RequestBody ShopModel shopModel) {
		return informationShopService.updateActive(shopModel);
	}

	// update thông tin cửa hàng.
	@PutMapping("/form/{id}")
	public ShopModel updateInformation(@PathVariable("id") Integer id, @RequestBody ShopModel shopModel) {
		return informationShopService.updateInformation(shopModel);
	}

	// Xóa một cửa hàng.
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		informationShopService.delete(id);
	}

}
