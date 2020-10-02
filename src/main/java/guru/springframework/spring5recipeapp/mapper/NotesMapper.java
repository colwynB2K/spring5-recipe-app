package guru.springframework.spring5recipeapp.mapper;

import guru.springframework.spring5recipeapp.domain.Notes;
import guru.springframework.spring5recipeapp.dto.NotesDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {RecipeMapper.class})
public interface NotesMapper {

    @Mappings({
            @Mapping(target = "recipe.categories", ignore = true),
            @Mapping(target = "recipe.ingredients", ignore = true),
            @Mapping(target = "recipe.notes", ignore = true)
    })
    NotesDTO toDTO(Notes Notes);

    @Mappings({
            @Mapping(target = "recipe.categories", ignore = true),
            @Mapping(target = "recipe.ingredients", ignore = true),
            @Mapping(target = "recipe.notes", ignore = true)
    })
    Notes toEntity(NotesDTO NotesDTO);
}
