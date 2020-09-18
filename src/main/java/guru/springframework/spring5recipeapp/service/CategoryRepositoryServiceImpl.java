package guru.springframework.spring5recipeapp.service;

import guru.springframework.spring5recipeapp.domain.Category;
import guru.springframework.spring5recipeapp.dto.CategoryDTO;
import guru.springframework.spring5recipeapp.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CategoryRepositoryServiceImpl implements CategoryService {

    final private CategoryRepository categoryRepository;

    @Autowired
    public CategoryRepositoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        Category category =  categoryRepository.findByName(name).orElseThrow(() -> new RuntimeException("Category " + name + " not " +
                "found!"));

        CategoryDTO categoryDTO = new CategoryDTO();
        BeanUtils.copyProperties(category, categoryDTO);

        return categoryDTO;
    }
}
