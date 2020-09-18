package guru.springframework.spring5recipeapp.service;

import guru.springframework.spring5recipeapp.dto.CategoryDTO;

public interface CategoryService {

    CategoryDTO getCategoryByName(String name);
}
