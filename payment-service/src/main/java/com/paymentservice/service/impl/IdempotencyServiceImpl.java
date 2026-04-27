package com.paymentservice.service.impl;

import com.paymentservice.entity.IdempotencyKey;
import com.paymentservice.enums.IdempotencyKeyStatus;
import com.paymentservice.exception.IdempotencyKeyExistsException;
import com.paymentservice.repository.IdempotencyRepository;
import com.paymentservice.service.IdempotencyService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IdempotencyServiceImpl implements IdempotencyService {

    private final IdempotencyRepository repository;

    @Transactional
    @Override
    public void createPendingKey(String key) {
        var newKey = new IdempotencyKey(key, IdempotencyKeyStatus.PENDING);
        try {
            repository.save(newKey);
        } catch (DataIntegrityViolationException e) {
            throw new IdempotencyKeyExistsException("Key already exists", e);
        }
    }

    @Override
    public Optional<IdempotencyKey> getByKey(String key) {
        return repository.findById(key);
    }

    @Transactional
    @Override
    public void markAsCompleted(String key, String responseData, int statusCode) {
        var keyEntity = getByKey(key).orElseThrow(() -> new EntityNotFoundException("Key not found"));
        keyEntity.setStatus(IdempotencyKeyStatus.COMPLETED);
        keyEntity.setStatusCode(statusCode);
        keyEntity.setResponse(responseData);
        repository.save(keyEntity);
    }
}
