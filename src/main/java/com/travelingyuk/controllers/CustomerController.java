package com.travelingyuk.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.travelingyuk.dto.CustomerData;
import com.travelingyuk.dto.ResponseData;
import com.travelingyuk.models.entities.Customer;
import com.travelingyuk.service.CustomerService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    
    @Autowired
    private CustomerService customerService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public Iterable<Customer> findAll() {
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public Customer findOne(@PathVariable("id") Long id) {
        return customerService.findOne(id);
    }

    @PostMapping
    public ResponseEntity<ResponseData<Customer>> create(@Valid @RequestBody CustomerData customerData, Errors errors) {
        ResponseData<Customer> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Customer customer = modelMapper.map(customerData, Customer.class);
        responseData.setStatus(true);
        responseData.getMessage().add("Created Succesfully");
        responseData.setPayload(customerService.createCustomer(customer));
        return ResponseEntity.ok(responseData);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Customer>> update(@Valid @RequestBody CustomerData customerData, Errors errors) {
        ResponseData<Customer> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Customer customer = modelMapper.map(customerData, Customer.class);
        responseData.setStatus(true);
        responseData.getMessage().add("Created Succesfully");
        responseData.setPayload(customerService.updateCustomer(customer.getId(),customer.getName(),customer.getAddress(),customer.getEmail()));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Void>> removeOne(@PathVariable("id") Long id) {
        ResponseData<Void> responseData = new ResponseData<>();
    
        if (customerService.deleteCustomer(id)) {
            responseData.setStatus(true);
            responseData.getMessage().add("Category with ID " + id + " deleted successfully.");
            return ResponseEntity.ok(responseData);
        } else {
            responseData.getMessage().add("Category with ID " + id + " not found.");
            responseData.setStatus(false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }
    }
    

}
