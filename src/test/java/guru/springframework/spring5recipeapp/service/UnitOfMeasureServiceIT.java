package guru.springframework.spring5recipeapp.service;

import guru.springframework.spring5recipeapp.domain.UnitOfMeasure;
import guru.springframework.spring5recipeapp.repository.UnitOfMeasureRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest                                                                                    // Do not load the full Spring Application (Beans via component scanning), but slice the application and only load the Spring Data JPA Repository related beans)
class UnitOfMeasureServiceIT {

    private final static String UOM_CUP = "Cup";

    @Autowired
    private UnitOfMeasureRepository unitOfMeasureRepository;                                    // Because we are wiring in the actual bean and not a mock and calling the real persistence layer this is an integration test

    @Test
    void getUOMByName() {
        UnitOfMeasure actualUOM =  unitOfMeasureRepository.findByName(UOM_CUP).get();

        assertEquals(UOM_CUP, actualUOM.getName());
    }
}