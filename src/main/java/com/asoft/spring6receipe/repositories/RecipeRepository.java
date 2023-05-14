package com.asoft.spring6receipe.repositories;

import com.asoft.spring6receipe.model.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe,Long> {
}
