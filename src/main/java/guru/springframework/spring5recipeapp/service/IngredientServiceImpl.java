package guru.springframework.spring5recipeapp.service;

import guru.springframework.spring5recipeapp.dto.IngredientDTO;
import guru.springframework.spring5recipeapp.dto.RecipeDTO;
import guru.springframework.spring5recipeapp.dto.UnitOfMeasureDTO;
import guru.springframework.spring5recipeapp.exception.ObjectNotFoundException;
import guru.springframework.spring5recipeapp.mapper.IngredientMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class IngredientServiceImpl implements IngredientService {

    private final RecipeService recipeService;
    private final IngredientMapper ingredientMapper;
    private final UnitOfMeasureService unitOfMeasureService;

    @Autowired
    public IngredientServiceImpl(RecipeService recipeService, IngredientMapper ingredientMapper,
                                 UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientMapper = ingredientMapper;
        this.unitOfMeasureService = unitOfMeasureService;
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

    @Override
    public IngredientDTO saveIngredientOnRecipe(Long recipeId, IngredientDTO ingredientDTO) {
        RecipeDTO recipeDTO = recipeService.findById(recipeId);

        Optional<IngredientDTO> recipeIngredientDTO = recipeDTO.getIngredients()
                                                                .stream()
                                                                .filter(ingredient -> ingredient.getId().equals(ingredientDTO.getId()))
                                                                .findFirst();

        UnitOfMeasureDTO unitOfMeasureDTO = unitOfMeasureService.findById(ingredientDTO.getUnitOfMeasure().getId());  // The incoming uomDTO will only contain an id from the form select box here

        if (recipeIngredientDTO.isPresent()) {
            recipeIngredientDTO.get().setName(ingredientDTO.getName());
            recipeIngredientDTO.get().setAmount(ingredientDTO.getAmount());
            recipeIngredientDTO.get().setUnitOfMeasure(unitOfMeasureDTO);
        } else {
            ingredientDTO.setUnitOfMeasure(unitOfMeasureDTO);   // Enrich the incoming ingredientDTO which only contains a uom id with a fully filled UnitOfMeasureDTO
            recipeDTO.getIngredients().add(ingredientDTO);
        }

        RecipeDTO savedRecipeDTO = recipeService.save(recipeDTO);

        return savedRecipeDTO.getIngredients()
                            .stream()
                            .filter(recipeIngredient -> recipeIngredient.getId().equals(ingredientDTO.getId()))
                            .findFirst()
                            .get();
    }
}
