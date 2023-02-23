package org.feb14.pizzeria.repository;

import org.feb14.pizzeria.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

}
