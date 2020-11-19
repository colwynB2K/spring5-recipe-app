package guru.springframework.spring5recipeapp.controller;

import guru.springframework.spring5recipeapp.dto.CategoryDTO;
import guru.springframework.spring5recipeapp.dto.RecipeDTO;
import guru.springframework.spring5recipeapp.exception.ObjectNotFoundException;
import guru.springframework.spring5recipeapp.service.CategoryService;
import guru.springframework.spring5recipeapp.service.ImageService;
import guru.springframework.spring5recipeapp.service.RecipeService;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {

    @Mock
    private RecipeService mockRecipeService;

    @Mock
    private CategoryService mockCategoryService;

    @Mock
    ImageService mockImageService;

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
    void showRecipeDetail() throws Exception {
        // given
        when(mockRecipeService.findById(ID)).thenReturn(recipeDTO);

        // whwn
        mockMvc.perform(get("/recipes/" + ID))
                .andExpect(model().attribute("recipe", equalTo(recipeDTO)))
                .andExpect(status().isOk())
                .andExpect(view().name("recipes/detail"));

        // then
        verify(mockRecipeService).findById(ID);
    }

    @Test
    void showRecipeDetail_Should_Return_A_404_For_Unknown_Id() throws Exception {
        // given
        when(mockRecipeService.findById(anyLong())).thenThrow(ObjectNotFoundException.class);

        // when
        mockMvc.perform(get("/recipes/" + ID))
                .andExpect(status().isNotFound());
    }

    @Test
    void showRecipeForm_For_Specified_Recipe_Id() throws Exception {
        // given
        when(mockRecipeService.findById(ID)).thenReturn(recipeDTO);
        when(mockCategoryService.findAll()).thenReturn(categoryDTOs);

        // when
        mockMvc.perform(get("/recipes/edit/" + ID))
                .andExpect(model().attribute("recipe", equalTo(recipeDTO)))
                .andExpect(model().attribute("categories", equalTo(categoryDTOs)))
                .andExpect(status().isOk())
                .andExpect(view().name("recipes/form"));

        // then
        verify(mockRecipeService).findById(ID);
        verify(mockCategoryService).findAll();
    }

    @Test
    void showRecipeForm_When_RecipeId_Is_Null() throws Exception {
        // given
        when(mockCategoryService.findAll()).thenReturn(categoryDTOs);

        // when
        mockMvc.perform(get("/recipes/edit"))
                .andExpect(model().attributeExists("recipe"))
                .andExpect(model().attribute("categories", equalTo(categoryDTOs)))
                .andExpect(status().isOk())
                .andExpect(view().name("recipes/form"));

        // then
        verify(mockCategoryService).findAll();
    }

    @Test
    void saveRecipe() throws Exception {
        // given
        when(mockRecipeService.save(any(RecipeDTO.class))).thenReturn(recipeDTO);

        // when
        mockMvc.perform(
                    post("/recipes")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .param("id", "")
                    .param("description", "some short description")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipes/" + ID));

        // then
        verify(mockRecipeService).save(any(RecipeDTO.class));
    }

    @Test
    void deleteById() throws Exception {
        mockMvc.perform(get("/recipes/" + ID + "/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        verify(mockRecipeService).deleteById(recipeDTO.getId());
    }

    @Test
    void showImageForm() throws Exception {
        // when
        when(mockRecipeService.findById(anyLong())).thenReturn(recipeDTO);

        // then
        mockMvc.perform(get("/recipes/1/image/edit"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("recipe", equalTo(recipeDTO)))
                .andExpect(view().name("recipes/images/form"));

        verify(mockRecipeService).findById(anyLong());
    }

    @Test
    void handleImagePost() throws Exception {
        // given
        Long recipeId = 1L;
        MockMultipartFile mockMultipartFile = new MockMultipartFile("imagefile", "testing.txt", "text/plain", "Spring Framework Guru".getBytes());

        // then
        mockMvc.perform(multipart("/recipes/" + recipeId + "/image").file(mockMultipartFile))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("location", "/recipes/1"))
                .andExpect(view().name("redirect:/recipes/" + recipeId));

        verify(mockImageService).saveOnRecipe(anyLong(), any(MultipartFile.class));
    }

    @Test
    void renderImageFromDB() throws Exception {
        //given
        Long recipeId = 1L;
        String s = "fake Image content";
        recipeDTO.setImage(ArrayUtils.toObject(s.getBytes()));
        when(mockRecipeService.findById(recipeId)).thenReturn(recipeDTO);

        // when
        MockHttpServletResponse response = mockMvc.perform(get("/recipes/" + recipeId + "/image"))
                                                .andExpect(status().isOk())
                                                .andReturn().getResponse();

        byte[] responseBytes = response.getContentAsByteArray();

        // then
        assertEquals(s.getBytes().length, responseBytes.length);
    }
}