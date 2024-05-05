package com.travelingyuk.models.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.travelingyuk.models.entities.Category;

@Repository
public interface CategoryRepo extends CrudRepository<Category, Long> {
    
}
