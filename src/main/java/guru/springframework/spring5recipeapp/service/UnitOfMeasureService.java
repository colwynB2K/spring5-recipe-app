package guru.springframework.spring5recipeapp.service;

import guru.springframework.spring5recipeapp.domain.UnitOfMeasure;

public interface UnitOfMeasureService {

    UnitOfMeasure getUOMByName(String name);
}
