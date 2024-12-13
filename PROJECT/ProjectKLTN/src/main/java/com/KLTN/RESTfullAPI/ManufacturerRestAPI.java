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

import com.KLTN.Entity.Manufacturer;
import com.KLTN.Model.ManufacturerModel;
import com.KLTN.Service.ManufacturerService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/manufactures")
public class ManufacturerRestAPI {
	@Autowired
	private ManufacturerService manufacturerService;

	// lấy danh sách nhà sản xuất.
	@GetMapping()
	public List<Manufacturer> getAll() {
		return manufacturerService.findAll();
	}

	// Lấy một nhà sản xuất theo cái id được truyền vào.
	@GetMapping("/form/{id}")
	public ManufacturerModel getOneUserById(@PathVariable("id") Integer id) {
		return manufacturerService.getOneManufacturerById(id);
	}

	// Thêm mới một nhà sản xuất.
	@PostMapping("/form")
	public ManufacturerModel create(@RequestBody ManufacturerModel manufacturerModel) {
		return manufacturerService.createManufacturer(manufacturerModel);
	}

	// udpate lại nhà sản xuất.
	@PutMapping("/form/{id}")
	public ManufacturerModel update(@PathVariable("id") Integer id, @RequestBody ManufacturerModel manufacturerModel) {
		return manufacturerService.updateManufacturer(manufacturerModel);
	}

	// Xóa một nhà sản xuất.
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		manufacturerService.delete(id);
	}

}
