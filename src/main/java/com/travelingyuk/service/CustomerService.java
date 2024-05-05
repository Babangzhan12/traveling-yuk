package com.travelingyuk.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelingyuk.models.entities.Customer;
import com.travelingyuk.models.repository.CustomerRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    public Customer createCustomer(Customer customer){
        return customerRepo.save(customer);
    }

    public Customer updateCustomer(Long id, String name, String address, String email) {
        return customerRepo.findById(id)
                .map(customer -> {
                    customer.setName(name);
                    customer.setAddress(address);
                    customer.setEmail(email);
                    return customerRepo.save(customer);
                })
                .orElseThrow(() -> new IllegalArgumentException("Customer with ID " + id + " not found."));
    }

    public Customer findOne(Long id){
        Optional<Customer> customer = customerRepo.findById(id);
        if(!customer.isPresent()){
            return null;
        }
        return customer.get();
    }

    public Iterable<Customer> findAll(){
        return customerRepo.findAll();
    }

    public boolean deleteCustomer(Long id) {
        Optional<Customer> customerOptional = customerRepo.findById(id);
        if (customerOptional.isPresent()) {
            customerRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public Customer findByEmail(String email){
        return customerRepo.findByEmail(email);
    }

}
