package com.dodson.spring_web_recipe.repositories;

import com.dodson.spring_web_recipe.domain.UnitOfMeasure;

import org.springframework.data.repository.CrudRepository;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
    
}
