package com.travelingyuk.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.travelingyuk.models.entities.Customer;

public interface CustomerRepo extends CrudRepository<Customer, Long> {

    Customer findByEmail(String email);

}
