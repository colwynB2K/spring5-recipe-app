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
        UnitOfMeasureDTO unitOfMeasureDTO = unitOfMeasureService.findById(ingredientDTO.getUnitOfMeasure().getId());  // The incoming uomDTO will only contain an id from the form select box here

        Optional<IngredientDTO> recipeIngredientDTO = Optional.empty();
        if (ingredientDTO.getId() != null) {                                                    // If we are updating an existing ingredient, it has an id with which we can lookup its saved version from the recipe in the database
            recipeIngredientDTO = recipeDTO.getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(ingredientDTO.getId()))
                    .findFirst();
        }

        if (recipeIngredientDTO.isPresent()) {                                                  // If we indeed found a saved version of this ingredient, just update its fields
            recipeIngredientDTO.get().setName(ingredientDTO.getName());
            recipeIngredientDTO.get().setAmount(ingredientDTO.getAmount());
            recipeIngredientDTO.get().setUnitOfMeasure(unitOfMeasureDTO);
        } else {                                                                                // If not, a new ingredient is being added, so we need to make sure we set its relationships and add it to the recipeDTO we got from the database
            ingredientDTO.setRecipe(recipeDTO);
            ingredientDTO.setUnitOfMeasure(unitOfMeasureDTO);                                   // Enrich the incoming ingredientDTO which only contains a uom id with a fully filled UnitOfMeasureDTO
            recipeDTO.getIngredients().add(ingredientDTO);
        }

        RecipeDTO savedRecipeDTO = recipeService.save(recipeDTO);                               // Now save the update recipeDTO which originally came from the database back to the database

        // To be able to display the saved ingredient data (for further editing), we need to return the savedIngreddient (by fetching it from the saved Recipe again)
        // When we are updating an existing ingredient the incoming ingredientDTO will have an id by which we can fetch the saved Ingredient from the saved Recipe
        Optional<IngredientDTO> savedIngredientOptional = null;
        if (ingredientDTO.getId() != null) {
            savedIngredientOptional = savedRecipeDTO.getIngredients()
                    .stream()
                    .filter(recipeIngredient -> recipeIngredient.getId().equals(ingredientDTO.getId()))
                    .findFirst();
        } else {
            // If we are saving a NEW ingredient the incoming ingredientDTO will NOT have an id, so we can only fetch it from the saved Recipe
            // by looking for an Ingredient with the same Name value.
            // That may not be 100% safe, but let's be honest... if you would have 2 identical ingredients on the same recipe... shit is already hitting the fan
            savedIngredientOptional = savedRecipeDTO.getIngredients()
                    .stream()
                    .filter(recipeIngredient -> recipeIngredient.getName().equals(ingredientDTO.getName()))
                    .filter(recipeIngredient -> recipeIngredient.getAmount().equals(ingredientDTO.getAmount())) // Just in case someone likes to add multiple ingredient records with same name
                    .filter(recipeIngredient -> recipeIngredient.getUnitOfMeasure().getId().equals(ingredientDTO.getUnitOfMeasure().getId())) // Filtering UOM (object) doesn't work, you need to filter on a property
                    .findFirst();
        }

        return savedIngredientOptional.get();
    }
}
