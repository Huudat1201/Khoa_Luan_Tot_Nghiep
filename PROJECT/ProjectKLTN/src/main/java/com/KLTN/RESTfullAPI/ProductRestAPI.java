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

import com.KLTN.Entity.Product;
import com.KLTN.Model.ProductModel;
import com.KLTN.Service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/product")
public class ProductRestAPI {

	@Autowired
	private ProductService productService;

	// Lấy danh sách sản phẩm.
	@GetMapping()
	public List<Product> getAll() {
		return productService.findAll();
	}

	// Lấy một sản phẩm theo id truyền vào.
	@GetMapping("/form/{id}")
	public ProductModel getOneProductById(@PathVariable("id") Integer id) {
		return productService.getOneProductById(id);
	}

	// Tạo mới một sản phẩm.
	@PostMapping("/form")
	public ProductModel create(@RequestBody ProductModel productModel) {
		return productService.createProduct(productModel);
	}

	// Update sản phẩm theo cái id truyền vào.
	@PutMapping("/form/{id}")
	public ProductModel update(@PathVariable("id") Integer id, @RequestBody ProductModel productModel) {
		return productService.updateProduct(productModel);
	}

	// Xóa một sản phẩm theo id.
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		productService.deleteProduct(id);
	}

}
