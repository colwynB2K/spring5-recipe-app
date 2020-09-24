package guru.springframework.spring5recipeapp.mapper;

import guru.springframework.spring5recipeapp.domain.Category;
import guru.springframework.spring5recipeapp.dto.CategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(target = "recipes", ignore = true)
    Category CategoryDTOToCategory(CategoryDTO CategoryDTO);

    @Mapping(target = "recipes", ignore = true)
    CategoryDTO CategoryToCategoryDTO(Category Category);
}
