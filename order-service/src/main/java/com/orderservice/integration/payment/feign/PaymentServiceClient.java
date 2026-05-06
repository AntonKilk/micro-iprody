package com.orderservice.integration.payment.feign;

import com.orderservice.dto.request.PaymentRequest;
import com.orderservice.dto.response.PaymentResponse;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "payment-service", url = "http://localhost:8082")
public interface PaymentServiceClient {
    @PostMapping("/payments")
    @CircuitBreaker(name = "paymentClientCircuitBreaker")
    @Retry(name = "paymentClientRetry")
    @RateLimiter(name = "paymentClientRateLimiter")
    @Bulkhead(name = "paymentClientBulkhead")
    PaymentResponse createPaymentRequest(@RequestHeader("X-Idempotency-Key") String idempotencyKey,
                                         @RequestBody
                                         PaymentRequest req);
}
