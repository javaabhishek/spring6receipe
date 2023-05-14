package com.asoft.spring6receipe.repositories;

import com.asoft.spring6receipe.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category,Long> {
}
