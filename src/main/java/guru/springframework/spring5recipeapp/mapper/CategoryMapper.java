package guru.springframework.spring5recipeapp.mapper;

import guru.springframework.spring5recipeapp.domain.Category;
import guru.springframework.spring5recipeapp.dto.CategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category CategoryDTOToCategory(CategoryDTO CategoryDTO);

    CategoryDTO CategoryToCategoryDTO(Category Category);
}
