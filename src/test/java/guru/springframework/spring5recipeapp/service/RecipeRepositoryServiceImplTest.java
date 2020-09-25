package guru.springframework.spring5recipeapp.service;

import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.dto.RecipeDTO;
import guru.springframework.spring5recipeapp.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecipeRepositoryServiceImplTest {

    private final static Long ID = 1L;

    @Mock
    RecipeRepository mockRecipeRepository;

    @InjectMocks
    RecipeRepositoryServiceImpl recipeRepositoryService;

    private Recipe recipe;

    @BeforeEach
    void setUp() {
        recipe = new Recipe();
        recipe.setId(ID);
    }

    @Test
    void findAll() {
        Set<Recipe> expectedRecipes = new HashSet<>();
        expectedRecipes.add(new Recipe());

        // when (you call the findAll() method on the mocked repository, return this test set
        when(mockRecipeRepository.findAll()).thenReturn(expectedRecipes);
        Set<RecipeDTO> actualRecipes = recipeRepositoryService.findAll();                  // When calling the findAll() method on the recipeRepositoryServiceImpl (object under test)

        // then
        verify(mockRecipeRepository, times(1)).findAll();   // Verify that the findAll() method on the (mocked) repository was called precisely 1 time
        assertEquals(actualRecipes.size(), 1);                                    // Assert that the resulting Recipe Set has only 1 Recipe in it
    }

    @Test
    void findById() {
        //when
        when(mockRecipeRepository.findById(ID)).thenReturn(java.util.Optional.ofNullable(recipe));
        RecipeDTO actualRecipe = recipeRepositoryService.findById(ID);

        // then
        assertNotNull(actualRecipe);
        verify(mockRecipeRepository).findById(ID);
    }

    @Test
    void deleteById() {
        // when
        recipeRepositoryService.deleteById(ID);
        // no 'when' statement for the mockRecipeRepository, since the method has void return type

        // then
        verify(mockRecipeRepository).deleteById(ID);
    }
}