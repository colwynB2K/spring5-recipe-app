package guru.springframework.spring5recipeapp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NotesDTO {
    private Long id;
    private String notes;
    //private RecipeDTO recipe;
}
