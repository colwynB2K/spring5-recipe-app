package guru.springframework.spring5recipeapp.service;

import guru.springframework.spring5recipeapp.domain.Ingredient;
import guru.springframework.spring5recipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> findAll();

    Recipe addIngredientsToRecipe(Recipe recipe, Set<Ingredient> ingredients);
}
