package guru.springframework.spring5recipeapp.service;

import guru.springframework.spring5recipeapp.dto.IngredientDTO;
import guru.springframework.spring5recipeapp.dto.RecipeDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IngredientServiceImplTest {

    @Mock
    private RecipeService mockRecipeService;

    @InjectMocks
    private IngredientServiceImpl ingredientServiceImpl;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findByRecipeIdAndIngredientId() {
        Long recipeId = 1L;
        Long ingredientId = 2L;

        // given
        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setId(recipeId);
        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setId(ingredientId);
        ingredientDTO.setRecipeId(recipeId);

        Set<IngredientDTO> ingredientDTOs = new HashSet<>();
        ingredientDTOs.add(ingredientDTO);
        recipeDTO.setIngredients(ingredientDTOs);

        // when
        when(mockRecipeService.findById(anyLong())).thenReturn(recipeDTO);
        IngredientDTO actualIngredientDTO = ingredientServiceImpl.findByRecipeIdAndIngredientId(recipeId, ingredientId);

        verify(mockRecipeService).findById(recipeId);
        assertEquals(ingredientDTO, actualIngredientDTO);
    }
}