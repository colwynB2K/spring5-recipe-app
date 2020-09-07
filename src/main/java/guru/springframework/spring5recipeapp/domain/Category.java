package guru.springframework.spring5recipeapp.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }

    public Category addRecipe(Recipe recipe) {
        recipe.addCategory(this);

        return this;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
