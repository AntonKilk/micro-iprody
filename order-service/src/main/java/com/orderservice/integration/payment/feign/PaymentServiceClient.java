package com.orderservice.integration.payment.feign;

import com.orderservice.dto.request.PaymentRequest;
import com.orderservice.dto.response.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "payment-service", url = "http://localhost:8082")
public interface PaymentServiceClient {
    @PostMapping("/payments")
    PaymentResponse createPaymentRequest(@RequestHeader("X-Idempotency-Key") String idempotencyKey,
                                         @RequestBody
                                         PaymentRequest req);
}
