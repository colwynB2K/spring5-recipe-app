package guru.springframework.spring5recipeapp.service;

import guru.springframework.spring5recipeapp.domain.Category;

public interface CategoryService {

    Category getCategoryByName(String name);
}
