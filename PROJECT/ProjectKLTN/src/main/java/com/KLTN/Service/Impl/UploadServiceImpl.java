package com.KLTN.Service.Impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.KLTN.Service.UploadService;

@Service
public class UploadServiceImpl implements UploadService {
	
	@Autowired
	ServletContext app;
	

	/*
	 *  Lưu tệp được tải lên (dạng MultipartFile) vào một thư mục cụ thể.
	 *  Phương thức trả về đối tượng File sau khi lưu tệp vào thư mục.
	 */
	@Override
	public File save(MultipartFile file, String folder) {
		File dir = new File("src/main/resources/static/assets/images/" + folder);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		String name = file.getOriginalFilename();
		try {
			File savedFile = new File(dir, name);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(savedFile));
			stream.write(file.getBytes());
			stream.close();
			return savedFile;
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}

}
