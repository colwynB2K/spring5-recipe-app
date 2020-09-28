package guru.springframework.spring5recipeapp.service;

import guru.springframework.spring5recipeapp.dto.IngredientDTO;
import guru.springframework.spring5recipeapp.dto.RecipeDTO;
import guru.springframework.spring5recipeapp.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {

    private RecipeService recipeService;

    @Autowired
    public IngredientServiceImpl(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @Override
    public IngredientDTO findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        RecipeDTO recipeDTO = recipeService.findById(recipeId);

        Optional<IngredientDTO> ingredientDTOOptional = recipeDTO.getIngredients()
                                                                .stream()
                                                                .filter(ingredientDTO -> ingredientId.equals(ingredientDTO.getId()))
                                                                .findFirst();

        return ingredientDTOOptional.orElseThrow(() -> new ObjectNotFoundException("No ingredient found for id ' " + ingredientId + "'"));
    }
}
