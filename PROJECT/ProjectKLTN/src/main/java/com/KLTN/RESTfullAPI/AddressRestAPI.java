package com.KLTN.RESTfullAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.KLTN.Entity.Address;
import com.KLTN.Service.AddressService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest")
public class AddressRestAPI {

	@Autowired
	private AddressService addressService;

	// Tiến hành cái update địa chỉ.
	@PutMapping("/address/form/{id}")
	public Address update(@PathVariable("id") Integer id, @RequestBody Address address) {
		return addressService.updateAddress(address, id);
	}

}
