package com.travelingyuk.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelingyuk.models.entities.Category;
import com.travelingyuk.models.repository.CategoryRepo;

import jakarta.transaction.TransactionScoped;

@Service
@TransactionScoped
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    public Iterable<Category> findAll() {
        return categoryRepo.findAll();
    }

    public Category findOne(Long id) {
        Optional<Category> category = categoryRepo.findById(id);
        if (!category.isPresent()) {
            return null;
        }
        return category.get();
    }

    public Category updateCategory(Long id, String name) {
        return categoryRepo.findById(id)
                .map(category -> {
                    category.setName(name);
                    return categoryRepo.save(category);
                })
                .orElseThrow(() -> new IllegalArgumentException("Category with ID " + id + " not found."));
    }

    public Category createCategory(Category category) {
        return categoryRepo.save(category);
    }

    public boolean deleteCategory(Long id) {
        Optional<Category> categoryOptional = categoryRepo.findById(id);
        if (categoryOptional.isPresent()) {
            categoryRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
