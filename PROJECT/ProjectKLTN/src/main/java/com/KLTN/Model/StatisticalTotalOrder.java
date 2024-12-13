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
public class StatisticalTotalOrder {
	
	// Tổng số đơn hàng thành công (Total successful orders)
    long orderSuccess;

    // Tổng số đơn hàng bị hủy (Total canceled orders)
    long orderCancel;

    // Tổng số đơn hàng đang chờ (Total pending orders)
    long orderWait;

    // Tổng số đơn hàng đang vận chuyển (Total orders in transport)
    long orderTransport;
    
}
