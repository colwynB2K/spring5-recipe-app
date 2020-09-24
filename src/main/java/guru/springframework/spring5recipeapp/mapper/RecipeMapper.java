package guru.springframework.spring5recipeapp.mapper;

import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.dto.RecipeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RecipeMapper {
    RecipeMapper INSTANCE = Mappers.getMapper(RecipeMapper.class);

    Recipe recipeDTOToRecipe(RecipeDTO recipeDTO);

    RecipeDTO recipeToRecipeDTO(Recipe recipe);
}
