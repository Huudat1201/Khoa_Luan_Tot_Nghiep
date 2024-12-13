package com.KLTN.Controller.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.KLTN.Entity.Address;
import com.KLTN.Service.AddressService;

@Controller
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	// Trang chủ Address
	@GetMapping("/account/address")
	public String index() {
		return "user/account/account_address";
	}
	
	@ModelAttribute("listAddress")
	public List<Address> getListAddress(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		return addressService.findListAddressByEmail(username);
	}
	

	// Tạo mới Address
	@GetMapping("/account/address/add")
	public String add(Model model) {
		Address address = new Address();
		model.addAttribute("address", address);
		return "user/account/addaddress";
	}
	
	@PostMapping("/saveaddress")
	public String saveAddress(@ModelAttribute("address") Address address) {
		addressService.saveAddress(address);
		return "redirect:/account/address";
	}
	
	// Cập nhật Address
	@GetMapping("/account/address/update/{id}")
	public String update(@PathVariable("id") int id, Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		Address addressUpdate = addressService.findAddressById(username, id);
		
		model.addAttribute("addressUpdate", addressUpdate);
		return "user/account/updateaddress";
	}

	@PostMapping("/account/address/updateaddress/{id}")
	public String updateAddress(@PathVariable("id") int id, @ModelAttribute("addressUpdate") Address addressUpdate) {
		addressService.updateAddress(addressUpdate, id);
		return "redirect:/account/address";
	}
	
	// Xóa Address
	@GetMapping("/account/address/delete/{id}")
	public String add(@PathVariable("id") int id) {
		Address address = addressService.getAddressById(id);
		addressService.delete(address);
		return "redirect:/account/address";
	}

}
