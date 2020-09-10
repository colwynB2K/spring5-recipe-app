package guru.springframework.spring5recipeapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"categories", "notes"})
// Excluded categories and notes, but left in recipes for equals and hashCode generation as it seems an essential
// part of the recipe
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Count on the underlying persistence framework to generate an Id value from a sequence
    private Long id;

    @Lob
    // In the database this will be stored as a CharacterLOB
    private String cookInstructions;
    private String description;
    private Integer cookTimeMins;
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;
    @Lob
    // In the database this will be stored as a BinaryLOB
    private Byte[] image;
    private String name;
    private Integer prepTimeMins;
    private String source;
    private String url;
    private String yield;

    @ManyToMany
    @JoinTable(name = "recipe_category",                                    // If you don't specify this, Hibernate
            // will generate 2 mapping tables actually and we don't want that! Could be a good idea to use the names
            // of the tables you are associating separated by an _, so developers don't need to look into the table
            // constraint defenitions to figure out to which database columns these foreign keys are pointings
            joinColumns = @JoinColumn(name = "recipe_id"),                      // Specify the column where the id of
            // this entity will be stored
            inverseJoinColumns = @JoinColumn(name = "category_id")              // Specify the column where the id of
            // the associated entity will be stored
    )
    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private Set<Ingredient> ingredients = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    // One recipe will have only one associated notes record, when we delete the recipe also delete the notes
    private Notes notes;

    public Recipe addCategory(Category category) {
        this.categories.add(category);
        category.getRecipes().add(this);

        return this;
    }

    public Recipe addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
        ingredient.setRecipe(this);

        return this;
    }

    public Recipe addIngredients(Set<Ingredient> ingredients) {
        ingredients.stream().forEach(ingredient -> ingredient.setRecipe(this));
        this.setIngredients(ingredients);

        return this;
    }

    public Recipe addNotes(Notes notes) {
        this.setNotes(notes);
        notes.setRecipe(this);

        return this;
    }
}
