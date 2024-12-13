package com.KLTN.VNPay;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentDTO {
    @Data
    @Builder
    public static class VNPayResponse {
        private String code;        // Mã trạng thái
        private String message;     // Thông báo
        private String paymentUrl;  // URL thanh toán
    }
}
