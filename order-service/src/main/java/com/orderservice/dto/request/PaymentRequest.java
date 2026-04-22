package com.orderservice.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Payment request sent from order-service to payment-service to charge the customer for a specific order")
public record PaymentRequest(
        @Schema(description = "Identifier of the order being paid for", example = "1001")
        Long orderId,
        @Schema(description = "Identifier of the customer making the payment", example = "42")
        Long customerId,
        @Schema(description = "Amount to be charged, in the order's currency", example = "199.99")
        BigDecimal amount
) {
}
