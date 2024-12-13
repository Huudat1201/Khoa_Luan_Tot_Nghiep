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
public class BlogModel {
	
	// Mã định danh của bài blog (Unique identifier for the blog)
    int id;

    // Tiêu đề của bài blog (Title of the blog)
    String title;

    // Nội dung của bài blog (Content of the blog)
    String content;

    // Hình ảnh logo của bài blog (Logo image of the blog)
    String logo;

    // Ngày tải lên của bài blog (Upload date of the blog)
    String uploadDay;

    // Hình ảnh banner của bài blog (Banner image of the blog)
    String banner;

    // Trạng thái kích hoạt của bài blog (Activation status of the blog, whether it is active or not)
    boolean active;

    // Mô tả của bài blog (Description of the blog)
    String description;

    // Tên dùng để tìm kiếm bài blog (Searchable name of the blog)
    String nameSearch;
    
}
