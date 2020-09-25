package guru.springframework.spring5recipeapp.service;

import guru.springframework.spring5recipeapp.dto.RecipeDTO;

import java.util.Set;

public interface RecipeService {

    Set<RecipeDTO> findAll();

    RecipeDTO findById(Long id);

    RecipeDTO save(RecipeDTO recipeDTO);

    void deleteById(Long id);
}
