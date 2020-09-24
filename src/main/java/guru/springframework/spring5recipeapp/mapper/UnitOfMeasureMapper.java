package guru.springframework.spring5recipeapp.mapper;

import guru.springframework.spring5recipeapp.domain.UnitOfMeasure;
import guru.springframework.spring5recipeapp.dto.UnitOfMeasureDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UnitOfMeasureMapper {
    UnitOfMeasureMapper INSTANCE = Mappers.getMapper(UnitOfMeasureMapper.class);

    UnitOfMeasure UnitOfMeasureDTOToUnitOfMeasure(UnitOfMeasureDTO UnitOfMeasureDTO);

    UnitOfMeasureDTO UnitOfMeasureToUnitOfMeasureDTO(UnitOfMeasure UnitOfMeasure);
}
