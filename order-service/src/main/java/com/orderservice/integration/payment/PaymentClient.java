package com.orderservice.integration.payment;

import com.orderservice.dto.request.PaymentRequest;
import com.orderservice.dto.response.PaymentResponse;
import com.orderservice.integration.payment.feign.PaymentServiceClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import tools.jackson.databind.json.JsonMapper;

import java.nio.ByteBuffer;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class PaymentClient {
    private final PaymentServiceClient paymentServiceClient;
    private final JsonMapper mapper;

    public PaymentResponse createPaymentRequest(PaymentRequest req) {
        try {
            return paymentServiceClient.createPaymentRequest(req);
        } catch (FeignException ex) {
            return processException(ex);
        }
    }

    private  PaymentResponse processException(FeignException ex) {
        HttpStatusCode statusCode = HttpStatusCode.valueOf(ex.status());
        Optional<ByteBuffer> bodyOptional = ex.responseBody();
        if (isAcceptable(statusCode) && bodyOptional.isPresent()) {
            return getResponse(bodyOptional.get());
        } else {
            throw new RuntimeException("Could not create payment request");
        }
    }

    private boolean isAcceptable(HttpStatusCode statusCode) {
        return statusCode.is2xxSuccessful() || statusCode.isSameCodeAs(HttpStatus.CONFLICT);
    }

    private PaymentResponse getResponse(ByteBuffer body) {
        return mapper.readValue(body.array(), PaymentResponse.class);
    }
}
