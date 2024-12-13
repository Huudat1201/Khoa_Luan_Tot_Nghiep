package com.KLTN.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.KLTN.Entity.InformationShop;

public interface InformationShopRepository extends JpaRepository<InformationShop, Integer>{
	
	// Lấy một InformationShop (Thông tin Shop) chưa bị xóa và đang hoạt động có trạng thái là 1.
	@Query("SELECT i FROM InformationShop i WHERE i.Deleteday = null and i.active = 1")
	InformationShop getOneInformationShop();
	
	// Lấy tất cả các InformationShop (Thông tin Shop) chưa bị xóa.
	@Query("SELECT i FROM InformationShop i WHERE i.Deleteday = null")
	List<InformationShop> getListInformationShop();

}
