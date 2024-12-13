package com.KLTN.Service;

import java.util.List;

import com.KLTN.Entity.InformationShop;
import com.KLTN.Model.ShopModel;

public interface InformationShopService {
	
	// Trả về danh sách tất cả các cửa hàng.
	List<InformationShop> findAll();
	
	// Trả về thông tin của một cửa hàng.
	InformationShop getOneInformationShop();

	// Tạo một cửa hàng mới.
	ShopModel createInformationShop(ShopModel shopModel);
	
	// Cập nhật trạng thái hoạt động của cửa hàng.
	ShopModel updateActive(ShopModel shopModel);

	// Trả về thông tin một cửa hàng dựa trên ID.
	ShopModel getOneShopById(Integer id);
	
	// Cập nhật thông tin của một cửa hàng.
	ShopModel updateInformation(ShopModel shopModel);
	
	// Xóa một cửa hàng dựa trên ID.
	void delete(Integer id);

}
