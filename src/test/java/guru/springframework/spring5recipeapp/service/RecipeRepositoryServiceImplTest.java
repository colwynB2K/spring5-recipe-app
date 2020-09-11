package guru.springframework.spring5recipeapp.service;

import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecipeRepositoryServiceImplTest {

    RecipeRepositoryServiceImpl recipeRepositoryService;

    @Mock
    RecipeRepository mockRecipeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        recipeRepositoryService = new RecipeRepositoryServiceImpl(mockRecipeRepository);
    }

    @Test
    void findAll() {
        // given (a Set of Recipe objects)
        HashSet recipesData = new HashSet();
        recipesData.add(new Recipe());

        // when (you call the findAll() method on the mocked repository, return this test set
        when(mockRecipeRepository.findAll()).thenReturn(recipesData);
        Set<Recipe> recipes = recipeRepositoryService.findAll();                  // When calling the findAll() method on the recipeRepositoryServiceImpl (object under test)

        // then
        verify(mockRecipeRepository, times(1)).findAll();   // Verify that the findAll() method on the (mocked) repository was called precisely 1 time
        assertEquals(recipes.size(), 1);                                    // Assert that the resulting Recipe Set has only 1 Recipe in it
    }
}