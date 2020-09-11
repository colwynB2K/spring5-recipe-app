package guru.springframework.spring5recipeapp.controller;

import guru.springframework.spring5recipeapp.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class IndexControllerTest {

    private IndexController indexController;

    @Mock
    private RecipeService mockRecipeService;

    @Mock
    private Model mockModel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        indexController = new IndexController(mockRecipeService);
    }

    @Test
    void getIndexPage() {
        // given
        String expectedResult = "index";

        // when
        String actualResult = indexController.getIndexPage(mockModel);

        // then
        verify(mockRecipeService, times(1)).findAll();
        verify(mockModel, times(1)).addAttribute(eq("recipes"),anySet());
        assertEquals(expectedResult, actualResult);
    }
}