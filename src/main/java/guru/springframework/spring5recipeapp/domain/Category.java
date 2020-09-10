package guru.springframework.spring5recipeapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = "recipes")
@Entity
// Project Lombok annotation which allows us not to code getter/setters/toString()/equals()
// /hashCode/RequiredArgsConstructor so you can leave all this code out
@NamedEntityGraph(name = "Category.recipes",
        attributeNodes = @NamedAttributeNode("recipes")
)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(
            mappedBy = "categories"                             // As basically we defined all aspects of this relationship already on the categories property in the Recipe entity, we can refer Hibernate to that here.  If you don't specify this, Hibernate will generated a second many-to-many mapping table!
    )
    private Set<Recipe> recipes = new HashSet<>();

    public Category addRecipe(Recipe recipe) {
        recipe.addCategory(this);

        return this;
    }
}
