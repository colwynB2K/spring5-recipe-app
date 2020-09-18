package guru.springframework.spring5recipeapp.controller;

import guru.springframework.spring5recipeapp.dto.RecipeDTO;
import guru.springframework.spring5recipeapp.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {

    @Mock
    private RecipeService mockRecipeService;

    @InjectMocks
    private RecipeController recipeController;

    MockMvc mockMvc;

    private RecipeDTO recipeDTO;
    private static final Long ID = 1L;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();

        recipeDTO = new RecipeDTO();
        recipeDTO.setId(ID);
        recipeDTO.setName("Spreddel");
    }

    @Test
    void showRecipe() throws Exception {
        // when
        when(mockRecipeService.findById(ID)).thenReturn(recipeDTO);

        // then
        mockMvc.perform(get("/recipes/" + ID))
                .andExpect(model().attribute("recipe", equalTo(recipeDTO)))
                .andExpect(status().isOk())
                .andExpect(view().name("recipes/detail"));

        verify(mockRecipeService).findById(ID);
    }
}