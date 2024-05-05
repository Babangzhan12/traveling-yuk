package com.travelingyuk.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelingyuk.models.entities.TransactionDetail;
import com.travelingyuk.models.repository.TransactionDetailsRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TransactionDetailsService {
    
    @Autowired
    private TransactionDetailsRepo transactionDetailsRepo;

    public Iterable<TransactionDetail> getAllTransactionDetails() {
        return transactionDetailsRepo.findAll();
    }

    public Optional<TransactionDetail> getTransactionDetailById(Long id) {
        return transactionDetailsRepo.findById(id);
    }

    public TransactionDetail createTransactionDetail(TransactionDetail transactionDetail) {
        return transactionDetailsRepo.save(transactionDetail);
    }

    public TransactionDetail updateTransactionDetail(Long id, TransactionDetail transactionDetail) {
        if (transactionDetailsRepo.existsById(id)) {
            transactionDetail.setId(id);
            return transactionDetailsRepo.save(transactionDetail);
        } else {
            throw new RuntimeException("Transaction detail not found with id: " + id);
        }
    }

    public void deleteTransactionDetail(Long id) {
        transactionDetailsRepo.deleteById(id);
    }
}
