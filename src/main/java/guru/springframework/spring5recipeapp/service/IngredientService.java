package guru.springframework.spring5recipeapp.service;

import guru.springframework.spring5recipeapp.dto.IngredientDTO;

public interface IngredientService {
    IngredientDTO findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

    IngredientDTO saveIngredientOnRecipe(Long recipeId, IngredientDTO ingredientDTO);
}
