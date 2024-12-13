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
public class CategoryModel {

	// Mã định danh của danh mục (Unique identifier for the category)
	int id;

	// Tên của danh mục (Name of the category)
	String name;

	// Hình ảnh banner của danh mục (Banner image of the category)
	String banner;

	// Logo của danh mục (Logo of the category)
	String logo;

	// Mô tả của danh mục (Description of the category)
	String describe;

	// Tên dùng để tìm kiếm danh mục (Searchable name of the category)
	String nameSearch;

}
