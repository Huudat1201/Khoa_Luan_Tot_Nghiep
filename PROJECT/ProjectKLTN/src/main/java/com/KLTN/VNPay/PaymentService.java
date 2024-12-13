package com.KLTN.VNPay;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.KLTN.BaseUrlUtil.VNPayUtil;
import com.KLTN.VNPayCore.Config.Payment.VNPAYConfig;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {
	private final VNPAYConfig vnPayConfig;

	public PaymentDTO.VNPayResponse createVnPayPayment(HttpServletRequest request) {
		long amount = Integer.parseInt(request.getParameter("amount")) * 100L;
		String bankCode = request.getParameter("bankCode");
		Map<String, String> vnpParamsMap = vnPayConfig.getVNPayConfig();
		vnpParamsMap.put("vnp_Amount", String.valueOf(amount));
		if (bankCode != null && !bankCode.isEmpty()) {
			vnpParamsMap.put("vnp_BankCode", bankCode);
		}
		vnpParamsMap.put("vnp_IpAddr", VNPayUtil.getIpAddress(request));
		// build query url
		String queryUrl = VNPayUtil.getPaymentURL(vnpParamsMap, true);
		String hashData = VNPayUtil.getPaymentURL(vnpParamsMap, false);
		String vnpSecureHash = VNPayUtil.hmacSHA512(vnPayConfig.getSecretKey(), hashData);
		queryUrl += "&vnp_SecureHash=" + vnpSecureHash;
		String paymentUrl = vnPayConfig.getVnp_PayUrl() + "?" + queryUrl;
		return PaymentDTO.VNPayResponse.builder().code("ok").message("success").paymentUrl(paymentUrl).build();
	}
}
