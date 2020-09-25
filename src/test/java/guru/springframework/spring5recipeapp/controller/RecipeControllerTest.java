package guru.springframework.spring5recipeapp.controller;

import guru.springframework.spring5recipeapp.dto.CategoryDTO;
import guru.springframework.spring5recipeapp.dto.RecipeDTO;
import guru.springframework.spring5recipeapp.service.CategoryService;
import guru.springframework.spring5recipeapp.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {

    @Mock
    private RecipeService mockRecipeService;

    @Mock
    private CategoryService mockCategoryService;

    @InjectMocks
    private RecipeController recipeController;

    MockMvc mockMvc;

    private RecipeDTO recipeDTO;
    private final Set<RecipeDTO> recipeDTOs = new HashSet<>();
    private final Set<CategoryDTO> categoryDTOs = new HashSet<>();
    private static final Long ID = 1L;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();

        recipeDTO = new RecipeDTO();
        recipeDTO.setId(ID);
        recipeDTO.setName("Spreddel");
        recipeDTOs.add(recipeDTO);

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTOs.add(categoryDTO);
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

    @Test
    void showRecipeForm_For_Specified_Recipe_Id() throws Exception {
        // when
        when(mockRecipeService.findById(ID)).thenReturn(recipeDTO);
        when(mockCategoryService.findAll()).thenReturn(categoryDTOs);

        // then
        mockMvc.perform(get("/recipes/edit/" + ID))
                .andExpect(model().attribute("recipe", equalTo(recipeDTO)))
                .andExpect(model().attribute("categories", equalTo(categoryDTOs)))
                .andExpect(status().isOk())
                .andExpect(view().name("recipes/form"));

        verify(mockRecipeService).findById(ID);
        verify(mockCategoryService).findAll();
    }

    @Test
    void showRecipeForm_When_RecipeId_Is_Null() throws Exception {
        // when
        when(mockCategoryService.findAll()).thenReturn(categoryDTOs);

        // then
        mockMvc.perform(get("/recipes/edit"))
                .andExpect(model().attribute("recipe", any(RecipeDTO.class)))
                .andExpect(model().attribute("categories", equalTo(categoryDTOs)))
                .andExpect(status().isOk())
                .andExpect(view().name("recipes/form"));

        verify(mockCategoryService).findAll();
    }

    @Test
    void saveRecipe() throws Exception {
        // when
        when(mockRecipeService.save(Mockito.any(RecipeDTO.class))).thenReturn(recipeDTO);

        // then
        mockMvc.perform(
                    post("/recipes")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .param("id", "")
                    .param("description", "some short description")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipes/" + ID));

        verify(mockRecipeService).save(Mockito.any(RecipeDTO.class));
    }
}