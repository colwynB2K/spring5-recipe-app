package guru.springframework.spring5recipeapp.dto;

import guru.springframework.spring5recipeapp.domain.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeDTO {
    private Long id;
    private String cookInstructions;
    private String description;
    private Integer cookTimeMins;
    private Difficulty difficulty;
    private Byte[] image;
    private String name;
    private Integer prepTimeMins;
    private String source;
    private String url;
    private String yield;
    private Set<CategoryDTO> categories = new HashSet<>();
    private Set<IngredientDTO> ingredients = new HashSet<>();
    private NotesDTO notes;
}
