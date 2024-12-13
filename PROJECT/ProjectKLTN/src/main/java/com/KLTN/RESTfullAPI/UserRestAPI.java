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

import com.KLTN.Entity.User;
import com.KLTN.Model.ChangePassModel;
import com.KLTN.Model.InformationModel;
import com.KLTN.Service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/user")
public class UserRestAPI {

	@Autowired
	private UserService userService;

	// Lấy danh sách người dùng (Chức năng dành cho admin).
	@GetMapping()
	public List<User> getAllUser() {
		return userService.findAllUserAnable();
	}

	// Lấy thông tin user theo cái email được truyền vào.
	@GetMapping("{email}")
	public User getUserByEmail(@PathVariable("email") String email) {
		return userService.findUserByEmail(email);
	}

	// Lấy thông tin người dùng.
	@GetMapping("/account")
	public InformationModel getUserAccount() {
		return userService.getUserAccount();
	}

	// lấy người dùng để tiến hành update.
	@GetMapping("/update/{id}")
	public InformationModel getOneUserById(@PathVariable("id") Integer id) {
		return userService.getOneUserById(id);
	}

	// lấy danh sách người dùng có chức năng là quản trị viên.
	@GetMapping("/list")
	public List<User> getAllUserNotRoleAdmin() {
		return userService.findAllUserNotRoleAdmin();
	}

	// Tạo mới một người dùng.
	@PostMapping
	public User create(@RequestBody User user) {
		return userService.create(user);
	}

	// Tạo mới người dùng.
	@PostMapping("/form")
	public InformationModel createUser(@RequestBody InformationModel informationModel) {
		return userService.createUser(informationModel);
	}

	// update lại người dùng theo cái id được truyền vào.
	@PutMapping("/form/{id}")
	public InformationModel update(@PathVariable("id") Integer id, @RequestBody InformationModel informationModel) {
		return userService.updateUser(informationModel, id);
	}

	// update lại cái user.
	@PutMapping("/account/update")
	public InformationModel update(@RequestBody InformationModel informationModel) {
		return userService.update(informationModel);
	}

	// Update lại cái mật khẩu người dùng.
	@PutMapping("/account/change-password")
	public ChangePassModel changePass(@RequestBody ChangePassModel changePassModel) {
		return userService.updatePass(changePassModel);
	}

	// Xóa một người dùng (Chức năng dành cho quản trị viên).
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		userService.deleteUser(id);
	}

}
