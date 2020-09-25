package guru.springframework.spring5recipeapp.mapper;

import guru.springframework.spring5recipeapp.domain.Ingredient;
import guru.springframework.spring5recipeapp.dto.IngredientDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IngredientMapper {
    IngredientMapper INSTANCE = Mappers.getMapper(IngredientMapper.class);

    Ingredient IngredientDTOToIngredient(IngredientDTO ingredientDTO);

    IngredientDTO IngredientToIngredientDTO(Ingredient ingredient);
}
