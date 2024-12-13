package com.KLTN.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.KLTN.Entity.Product;
import com.KLTN.Model.ProductModel;
import com.KLTN.Model.ShowProduct;

public interface ProductService {
	
	// Danh sách tất cả các sản phẩm.
	List<Product> findAll();
	
	// Danh sách các sản phẩm mới nhất.
	List<Product> getListLatestProduct();
	
	// Danh sách các sản phẩm được xem nhiều nhất.
	List<Product> getListViewsProduct();
	
	// Danh sách phân trang các sản phẩm dựa trên tên tìm kiếm.
	Page<Product> getListProductByNameSearch(String nameSearch, Pageable pageable);
	
	// Danh sách sản phẩm demo dựa trên tên tìm kiếm.
	List<Product> getDemo(String nameSearch);

	// Danh sách phân trang các sản phẩm trong khoảng giá dựa trên tên tìm kiếm.
	Page<Product> getListProductByPrice(String nameSearch, int minPrice, int maxPrice, Pageable pageable);

	// Danh sách phân trang các sản phẩm dựa trên các tiêu chí lọc như
	// giá, nhà sản xuất, thứ tự, trạng thái, tên, và danh mục.
	Page<ShowProduct> getListProductByFilter(String nameSearch, String price, String manu, String sort,
			Pageable pageable, String status, String name, String category);
	
	// Danh sách các sản phẩm liên quan dựa trên ID sản phẩm.
	List<Product> getListProductRelated(int id);
	
	// Danh sách các sản phẩm đang giảm giá.
	List<Product> getListProductSales();
	
	// Tạo một sản phẩm.
	ProductModel createProduct(ProductModel productModel);

	// Cập nhật sản phẩm mới.
	ProductModel updateProduct(ProductModel productModel);

	// Lấy một sản phẩm theo cái id truyền vào.
	ProductModel getOneProductById(Integer id);

	// Lấy một sản phẩm dựa trên tên tìm kiếm.
	Product getProductByNameSearch(String nameSearch);

	// Cập nhật số lượt xem dựa trên tên tìm kiếm.
	void updateView(String nameSearch);

	// Lưu sản phẩm xuống cơ sở dữ liệu.
	void saveProduct(Product product);	
	
	// Xóa một sản phẩm dựa trên cái id truyền vào.
	void deleteProduct(Integer id);
	
}
