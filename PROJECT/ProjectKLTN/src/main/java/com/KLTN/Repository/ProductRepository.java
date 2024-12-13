package com.KLTN.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.KLTN.Entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	// Truy vấn danh sách các đối tượng Product chưa bị xóa, vẫn còn bán.
	@Query("SELECT p FROM Product p WHERE p.Deleteday = null")
	List<Product> getListProduct();
	
	// Truy vấn một đối tượng Product (sản phẩm) chưa bị xóa dựa trên tên tìm kiếm 
	// khớp với tham số đầu vào nameSearch.
	@Query("SELECT p FROM Product p WHERE p.Deleteday = null AND p.Namesearch LIKE ?1")
	Product getProductByNameSearch(String nameSearch);
	
	// Truy vấn danh sách các đối tượng Product chưa bị xóa theo tên danh mục 
	// khớp với tham số nameSearch.
	@Query("SELECT p FROM Product p WHERE p.category.Namesearch LIKE ?1 AND p.Deleteday = null")
	List<Product> getListDemo(String nameSearch);
	
	// Truy vấn danh sách các sản phẩm chưa bị xóa liên quan dựa trên ID danh mục khớp với tham số manuId
	// và có trạng thái hoạt động là 1
	// Kết quả được sắp xếp theo số lượt xem (Views) giảm dần và chỉ lấy 10 sản phẩm đầu tiên.
	// -> Truy vấn lấy danh sách sản phẩm liên quan.
	@Query(value = "SELECT * FROM Products WHERE DeleteDay IS NULL AND Active = 1 AND Cate_Id = ?"
			+ " ORDER BY Views DESC LIMIT 10", nativeQuery = true)
	List<Product> getListProductRelated(int manuId);
	
	// Truy vấn 5 sản phẩm đang bán có doanh số cao nhất.
	@Query(value = "SELECT * FROM Products WHERE deleteDay IS NULL AND Active = 1 AND Sales != 0"
			+ " ORDER BY Views DESC LIMIT 5", nativeQuery = true)
	List<Product> getListProductSales();
	
	// Truy vấn danh sách các sản phẩm đang hoạt động tồn trong kho.
	@Query(value = "SELECT * FROM products where quality > 0 and deleteday IS NULL;", nativeQuery = true)
	List<Product> listStatisticalProductWarehouse();
	
	// Truy vấn 16 sản phẩm đang bán mới nhất được sắp xếp giảm dần theo ngày tạo.
	@Query(value = "SELECT * FROM Products WHERE deleteDay IS NULL AND Active = 1 ORDER BY CreateDay DESC LIMIT 16;",
			nativeQuery = true)
	List<Product> getListLatestProduct();
	
	// Truy vấn 14 sản phẩm còn đang hoạt động có lượt xem cao nhất.
	@Query(value = "SELECT * FROM Products WHERE DeleteDay IS NULL AND Active = 1 ORDER BY Views DESC LIMIT 14",
			nativeQuery = true)
	List<Product> getListViewsProduct();
	
	// Truy vấn danh sách các sản phẩm còn đang hoạt động theo tên danh mục sắp xếp theo ngày tạo 
	// (Createday) giảm dần với tên danh mục khớp với tham số nameSearch
	// Phương thức này trả về kết quả dưới dạng phân trang (Pageable).
	@Query("SELECT p FROM Product p WHERE p.category.Namesearch LIKE ?1 AND p.Deleteday = null AND p.active = 1"
			+ " ORDER BY p.Createday DESC")
	Page<Product> getListProductByNameSearch(String nameSearch, Pageable pageable);
	
	// Truy vấn danh sách các sản phẩm còn đang hoạt động được sắp xếp theo ngày tạo giảm dần
	// theo tên danh mục và khoảng giá
	// với tên danh mục khớp với tham số nameSearch và giá sản phẩm nằm trong khoảng từ minPrice đến maxPrice
	// Phương thức này trả về kết quả dưới dạng phân trang (Pageable).
	@Query("SELECT p FROM Product p WHERE p.category.Namesearch LIKE ?1 AND p.Deleteday = null AND p.active = 1"
			+ " AND p.price >= ?2 AND p.price <= ?3 ORDER BY p.Createday DESC")
	Page<Product> getListProductByPrice(String nameSearch, int minPrice, int maxPrice, Pageable pageable);

}
