package guru.springframework.spring5recipeapp.controller;

import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

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
        Set<Recipe> expectedRecipes = new HashSet<>();
        Recipe expectedRecipe1 = new Recipe();
        expectedRecipe1.setName("BLAAT");

        Recipe expectedRecipe2 = new Recipe();
        expectedRecipe2.setName("MEKKER");

        expectedRecipes.add(expectedRecipe1);
        expectedRecipes.add(expectedRecipe2);
        when(mockRecipeService.findAll()).thenReturn(expectedRecipes);

        ArgumentCaptor<Set<Recipe>> recipeSetArgumumentCaptor = ArgumentCaptor.forClass(Set.class);

        // when
        String actualResult = indexController.getIndexPage(mockModel);

        // then
        verify(mockRecipeService, times(1)).findAll();
        verify(mockModel, times(1)).addAttribute(eq("recipes"), recipeSetArgumumentCaptor.capture());
        Set<Recipe> actualRecipes = recipeSetArgumumentCaptor.getValue();
        assertEquals(2, actualRecipes.size());
        assertEquals(expectedResult, actualResult);
    }
}