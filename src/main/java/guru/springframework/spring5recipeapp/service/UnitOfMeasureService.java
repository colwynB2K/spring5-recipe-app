package guru.springframework.spring5recipeapp.service;

import guru.springframework.spring5recipeapp.dto.UnitOfMeasureDTO;

public interface UnitOfMeasureService {

    UnitOfMeasureDTO getUOMByName(String name);
}
