package com.asoft.spring6receipe.repositories;

import com.asoft.spring6receipe.model.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category,Long> {
    Optional<Category> findByDescription(String description);
}
