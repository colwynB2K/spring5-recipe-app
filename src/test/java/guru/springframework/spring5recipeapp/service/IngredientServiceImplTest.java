package guru.springframework.spring5recipeapp.service;

import guru.springframework.spring5recipeapp.dto.IngredientDTO;
import guru.springframework.spring5recipeapp.dto.RecipeDTO;
import guru.springframework.spring5recipeapp.dto.UnitOfMeasureDTO;
import guru.springframework.spring5recipeapp.mapper.IngredientMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IngredientServiceImplTest {

    @Mock
    private RecipeService mockRecipeService;

    @Mock
    private UnitOfMeasureService mockUnitOfMeasureService;

    @Mock
    IngredientMapper mockIngredientMapper;

    @InjectMocks
    private IngredientServiceImpl ingredientServiceImpl;

    private IngredientDTO ingredientDTO;
    private RecipeDTO recipeDTO;

    private Long recipeId = 1L;
    private Long ingredientId = 2L;
    private Long uomId = 3L;

    @BeforeEach
    void setUp() {
        recipeDTO = new RecipeDTO();
        recipeDTO.setId(recipeId);

        ingredientDTO = new IngredientDTO();
        ingredientDTO.setId(ingredientId);
    }

    @Test
    void findByRecipeIdAndIngredientId() {
        // given
        ingredientDTO.setRecipe(recipeDTO);

        Set<IngredientDTO> ingredientDTOs = new HashSet<>();
        ingredientDTOs.add(ingredientDTO);
        recipeDTO.setIngredients(ingredientDTOs);

        // when
        when(mockRecipeService.findById(anyLong())).thenReturn(recipeDTO);
        IngredientDTO actualIngredientDTO = ingredientServiceImpl.findByRecipeIdAndIngredientId(recipeId, ingredientId);

        verify(mockRecipeService).findById(recipeId);
        assertEquals(ingredientDTO, actualIngredientDTO);
    }

    @Test
    void saveIngredientOnRecipe() {
        // given
        UnitOfMeasureDTO uomDTO = new UnitOfMeasureDTO();
        uomDTO.setId(uomId);
        ingredientDTO.setUnitOfMeasure(uomDTO);

        RecipeDTO savedRecipeDTO = new RecipeDTO();
        savedRecipeDTO.setId(recipeId);
        savedRecipeDTO.getIngredients().add(new IngredientDTO());
        savedRecipeDTO.getIngredients().iterator().next().setId(ingredientId);

        // when
        when(mockRecipeService.findById(recipeId)).thenReturn(recipeDTO);
        when(mockUnitOfMeasureService.findById(anyLong())).thenReturn(uomDTO);
        when(mockRecipeService.save(any(RecipeDTO.class))).thenReturn(savedRecipeDTO);
        IngredientDTO savedIngredientDTO = ingredientServiceImpl.saveIngredientOnRecipe(recipeId, ingredientDTO);

        // then
        assertEquals(ingredientId, savedIngredientDTO.getId());
        verify(mockRecipeService).findById(recipeId);
        verify(mockUnitOfMeasureService).findById(anyLong());
        verify(mockRecipeService).save(any(RecipeDTO.class));
    }
}