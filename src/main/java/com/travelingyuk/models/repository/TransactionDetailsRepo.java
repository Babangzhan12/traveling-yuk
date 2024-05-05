package com.travelingyuk.models.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.travelingyuk.models.entities.TransactionDetail;

@Repository
public interface TransactionDetailsRepo extends CrudRepository<TransactionDetail, Long>{

    
}