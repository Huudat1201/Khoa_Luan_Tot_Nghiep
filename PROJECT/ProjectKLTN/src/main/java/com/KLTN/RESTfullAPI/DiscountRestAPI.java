package com.KLTN.RESTfullAPI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.KLTN.Entity.Discount;
import com.KLTN.Entity.User;
import com.KLTN.Model.DiscountModel;
import com.KLTN.Service.DiscountService;
import com.KLTN.Service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/discount")
public class DiscountRestAPI {

	@Autowired
	private UserService userService;

	@Autowired
	private DiscountService discountService;

	// Lấy danh sách mã giảm giá.
	@GetMapping()
	public List<Discount> getAll() {
		return discountService.findAll();
	}

	// Lấy một mã giảm giá theo cái id truyền vào.
	@GetMapping("/form/{id}")
	public DiscountModel getOneUserById(@PathVariable("id") Integer id) {
		return discountService.getOneDiscountById(id);
	}

	// Lấy danh sách mã giảm giá còn đang áp dụng.
	@GetMapping("/available")
	List<Discount> listDiscountAvailable(Model model) {
		return discountService.getListDiscountAvailable();
	}

	// Lấy danh sách người dùng đang hoạt động.
	@GetMapping("/user/list")
	List<User> listUser(Model model) {
		return userService.getListUserEnableSubscribe();
	}

	// Lấy một mã giảm giá theo cái code truyền vào (chức năng cho người dùng khi
	// đang order).
	@GetMapping("/apply/{code}")
	public Discount getDiscountByCode(@PathVariable("code") String code) {
		return discountService.getDiscountByCode(code);
	}

	// Tạo mới một mã giảm giá.
	@PostMapping("/form")
	public DiscountModel create(@RequestBody DiscountModel discountModel) {
		return discountService.createDiscount(discountModel);
	}

	// Gửi mã giảm giá cho người dùng.
	@PostMapping("/user/list/{discountId}")
	public User sendCodeDiscount(@PathVariable("discountId") Integer discountId, @RequestBody User user) {
		return discountService.sendCodeDiscount(discountId, user);
	}

	// update một mã giảm giá.
	@PutMapping("/form/{id}")
	public DiscountModel update(@PathVariable("id") Integer id, @RequestBody DiscountModel discountModel) {
		return discountService.updateDiscount(discountModel);
	}

	// Xóa một mã giảm giá.
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		discountService.delete(id);
	}

}
