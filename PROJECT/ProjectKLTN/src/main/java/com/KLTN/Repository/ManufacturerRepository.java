package com.KLTN.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.KLTN.Entity.Manufacturer;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer> {

	// Truy vấn danh sách các đối tượng Manufacturer (nhà sản xuất) chưa bị xóa
	// Kết quả được sắp xếp theo ID của nhà sản xuất (m.id).
	@Query("SELECT m FROM Manufacturer m WHERE m.Deleteday IS NULL ORDER BY m.id")
	List<Manufacturer> getListManufacturer();

}
