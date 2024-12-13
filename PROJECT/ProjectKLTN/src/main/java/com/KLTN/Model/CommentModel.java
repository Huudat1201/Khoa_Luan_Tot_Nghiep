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
public class CommentModel {
	
	// Mã định danh của sản phẩm (Unique identifier for the product being commented on)
    int productId;

    // Nội dung của bình luận (Content of the comment)
    String content;

    // Số sao đánh giá sản phẩm (Star rating given to the product)
    int star;
    
}
