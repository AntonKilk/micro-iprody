package com.orderservice.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "Payment result returned by payment-service after processing a payment request")
public record PaymentResponse(
        @Schema(description = "Identifier of the created payment record", example = "5001")
        Long id,
        @Schema(description = "Identifier of the order this payment relates to", example = "1001")
        Long orderId,
        @Schema(description = "Amount that was charged", example = "199.99")
        BigDecimal amount,
        @Schema(description = "Current payment status", example = "COMPLETED", allowableValues = {"PENDING", "COMPLETED", "FAILED"})
        String status,
        @Schema(description = "Timestamp when the payment was created", example = "2026-04-22T10:15:30")
        LocalDateTime createdAt
) {
}
