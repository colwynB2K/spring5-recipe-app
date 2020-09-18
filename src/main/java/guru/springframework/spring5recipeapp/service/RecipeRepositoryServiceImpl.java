package guru.springframework.spring5recipeapp.service;

import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.dto.RecipeDTO;
import guru.springframework.spring5recipeapp.exception.ObjectNotFoundException;
import guru.springframework.spring5recipeapp.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class RecipeRepositoryServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeRepositoryServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<RecipeDTO> findAll() {
        Set<RecipeDTO> recipes = new HashSet<>();
        recipeRepository.findAll()
                .iterator()
                .forEachRemaining(recipe -> {
                    RecipeDTO recipeDTO = new RecipeDTO();
                    BeanUtils.copyProperties(recipe, recipeDTO);
                    recipes.add(recipeDTO);
                });

        return recipes;
    }

    @Override
    public RecipeDTO findById(Long id) {
        Recipe recipe = recipeRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("No Recipe found for id '" + id + "'"));

        RecipeDTO recipeDTO = new RecipeDTO();
        BeanUtils.copyProperties(recipe, recipeDTO);

        return recipeDTO;
    }
}
