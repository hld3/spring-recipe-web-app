package com.dodson.spring_web_recipe.repositories;

import java.util.Optional;

import com.dodson.spring_web_recipe.domain.Category;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    
    Optional<Category> findByDescription(String description);
}
