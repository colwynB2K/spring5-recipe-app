package guru.springframework.spring5recipeapp.service;

import guru.springframework.spring5recipeapp.dto.CategoryDTO;
import guru.springframework.spring5recipeapp.dto.RecipeDTO;

import java.util.Set;

public interface RecipeService {

    Set<RecipeDTO> findAll();

    RecipeDTO findById(Long id);
}
