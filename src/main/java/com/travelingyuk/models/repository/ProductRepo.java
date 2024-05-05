package com.travelingyuk.models.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.travelingyuk.models.entities.Product;

import jakarta.transaction.Transactional;


@Repository
@Transactional
public interface ProductRepo extends CrudRepository<Product, Long>{


}
