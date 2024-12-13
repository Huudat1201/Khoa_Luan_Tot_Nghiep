package com.KLTN.Model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductModel {
	
	// Thông tin ID của sản phẩm (Product ID)
    int id;
    
    // Mã sản phẩm (Product Code)
    String code;
    
    // Tên sản phẩm (Product Name)
    String name;
    
    // Giá của sản phẩm (Product Price)
    int price;
    
    // Điểm thưởng cho sản phẩm (Reward Points)
    int point;
    
    // Số lượng sản phẩm trong kho (Product Quantity)
    int quality;
    
    // Mô tả sản phẩm (Product Description)
    String description;
    
    // Thông số kỹ thuật của sản phẩm (Product Specification)
    String specification;
    
    // Hình ảnh sản phẩm (Image 1)
    String image1;
    
    // Hình ảnh sản phẩm (Image 2)
    String image2;
    
    // Hình ảnh sản phẩm (Image 3)
    String image3;
    
    // Hình ảnh sản phẩm (Image 4)
    String image4;
    
    // Hình ảnh sản phẩm (Image 5)
    String image5;
    
    // Trạng thái hiển thị của sản phẩm (Active Status)
    boolean active;
    
    // ID của nhà sản xuất (Manufacturer ID)
    int manuId;
    
    // ID của danh mục sản phẩm (Category ID)
    int cateId;
    
    // Tên để tìm kiếm sản phẩm (Searchable Name)
    String nameSearch;
    
    // Màu sắc của sản phẩm (Product Color)
    String color;
    
    // Bộ nhớ của sản phẩm (Product Memory)
    String memory;
    
    // Số lượng sản phẩm đã bán (Sales Count)
    int sales;
	
}
