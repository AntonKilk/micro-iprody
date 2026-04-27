package com.paymentservice.repository;

import com.paymentservice.entity.IdempotencyKey;
import jakarta.persistence.LockModeType;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface IdempotencyRepository extends JpaRepository<IdempotencyKey, String> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @NonNull
    Optional<IdempotencyKey> findById(@NonNull String key);
}
