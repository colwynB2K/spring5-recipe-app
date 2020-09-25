package guru.springframework.spring5recipeapp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class IngredientDTO {
    private Long id;
    private BigDecimal amount;
    private String name;
    private UnitOfMeasureDTO unitOfMeasure;
    //private RecipeDTO recipe;
}
