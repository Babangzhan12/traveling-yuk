package com.travelingyuk.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelingyuk.models.entities.Customer;
import com.travelingyuk.models.entities.Product;
import com.travelingyuk.models.repository.ProductRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public Iterable<Product> findAll() {
        return productRepo.findAll();
    }

    public Product findOne(Long id) {
        Optional<Product> product = productRepo.findById(id);
        if (!product.isPresent()) {
            return null;
        }
        return product.get();
    }
    public Product save(Product product){
        return productRepo.save(product);
    }

    public void addCustomer(Customer customer, long productId){
        Product product = findOne(productId);
        if(product == null){
            throw new RuntimeException("Product with ID: "+productId+ " not found");
        }
        product.getCustomers().add(customer);
        save(product);
    }

    public boolean deleteProduct(Long id) {
        Optional<Product> optionalProduct = productRepo.findById(id);
        if (optionalProduct.isPresent()) {
            productRepo.deleteById(id);
            return true;
        }
        return false;
    }

}
