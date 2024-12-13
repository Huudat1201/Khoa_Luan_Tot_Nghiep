package com.KLTN.Service;

import java.util.List;

import com.KLTN.Entity.Manufacturer;
import com.KLTN.Model.ManufacturerModel;

public interface ManufacturerService {
	
	// Danh sách các nhà sản xuất.
	List<Manufacturer> findAll();
	
	// Tạo mới một nhà sản xuất.
	ManufacturerModel createManufacturer(ManufacturerModel manufacturerModel);

	// Lấy ra thông tin một nhà sản xuất.
	ManufacturerModel getOneManufacturerById(Integer id);

	// Cập nhật thông tin nhà sản xuất.
	ManufacturerModel updateManufacturer(ManufacturerModel manufacturerModel);

	// Xóa một nhà sản xuất theo id được cung cấp.
	void delete(Integer id);
	
}
