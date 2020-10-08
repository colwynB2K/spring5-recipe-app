package guru.springframework.spring5recipeapp.mapper;

import guru.springframework.spring5recipeapp.domain.Ingredient;
import guru.springframework.spring5recipeapp.dto.IngredientDTO;
import org.mapstruct.*;

import java.util.Set;

@Mapper(componentModel = "spring", uses = {RecipeMapper.class})
public interface IngredientMapper {

    IngredientDTO toDTO(Ingredient ingredient);

    @Named("IngredientIgnoreRecipeChildObjects")
    @Mappings({
            @Mapping(target = "recipe.categories", ignore = true),
            @Mapping(target = "recipe.ingredients", ignore = true),
            @Mapping(target = "recipe.notes", ignore = true)
    })
    IngredientDTO toDTOIgnoreRecipeChildObjects(Ingredient ingredient);

    @Named("IngredientSetIgnoreRecipeChildObjects")
    @IterableMapping(qualifiedByName = "IngredientIgnoreRecipeChildObjects")
    Set<IngredientDTO> toDTOSetIgnoreRecipeChildObjects(Set<Ingredient> ingredients);

    @Named("IngredientIgnoreRecipeChildObjects")
    @Mappings({
            @Mapping(target = "recipe.categories", ignore = true),
            @Mapping(target = "recipe.ingredients", ignore = true),
            @Mapping(target = "recipe.notes", ignore = true)
    })
    Ingredient toEntityIgnoreRecipeChildObjects(IngredientDTO ingredientDTO);

    @Named("IngredientSetIgnoreRecipeChildObjects")
    @IterableMapping(qualifiedByName = "IngredientIgnoreRecipeChildObjects")
    Set<Ingredient> toEntitySetIgnoreRecipeChildObjects(Set<IngredientDTO> ingredientDTOs);
}
