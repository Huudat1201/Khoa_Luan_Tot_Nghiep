package com.KLTN.Service;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
	
	// Lưu tệp được tải lên (dạng MultipartFile) vào một thư mục cụ thể.
	// Phương thức trả về đối tượng File sau khi lưu tệp vào thư mục.
	File save(MultipartFile file, String folder);

}
