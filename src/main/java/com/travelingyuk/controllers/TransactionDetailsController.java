package com.travelingyuk.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelingyuk.models.entities.TransactionDetail;
import com.travelingyuk.service.TransactionDetailsService;

@RestController
@RequestMapping("/api/transaction-details")
public class TransactionDetailsController {

    @Autowired
    private TransactionDetailsService transactionDetailsService;
    
    @GetMapping
    public Iterable<TransactionDetail> findAll() {
        return transactionDetailsService.getAllTransactionDetails();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDetail> getTransactionDetailById(@PathVariable("id") Long id) {
        return transactionDetailsService.getTransactionDetailById(id)
                .map(transactionDetail -> new ResponseEntity<>(transactionDetail, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<TransactionDetail> createTransactionDetail(@RequestBody TransactionDetail transactionDetail) {
        TransactionDetail createdTransactionDetail = transactionDetailsService.createTransactionDetail(transactionDetail);
        return new ResponseEntity<>(createdTransactionDetail, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionDetail> updateTransactionDetail(@PathVariable("id") Long id,
                                                                     @RequestBody TransactionDetail transactionDetail) {
        TransactionDetail updatedTransactionDetail = transactionDetailsService.updateTransactionDetail(id, transactionDetail);
        return new ResponseEntity<>(updatedTransactionDetail, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransactionDetail(@PathVariable("id") Long id) {
        transactionDetailsService.deleteTransactionDetail(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
