package guru.springframework.spring5recipeapp.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Count on the underlying persistence framework to generate an Id value from a sequence
    private Long id;

    private Integer cookTimeMins;
    private String description;
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;
    @Lob
    // In the database this will be stored as a BinaryLOB
    private Byte[] image;
    private Integer prepTimeMins;
    private Integer servings;
    private String source;
    private String url;

    @ManyToMany(fetch = FetchType.LAZY)                                     // Default fetch type for Many side
    @JoinTable(name = "recipe_category",                                    // If you don't specify this, Hibernate will generate 2 mapping tables actually and we don't want that! Could be a good idea to use the names of the tables you are associating separated by an _, so developers don't need to look into the table constraint defenitions to figure out to which database columns these foreign keys are pointings
        joinColumns = @JoinColumn(name = "recipe_id"),                      // Specify the column where the id of this entity will be stored
        inverseJoinColumns = @JoinColumn(name = "category_id")              // Specify the column where the id of the associated entity will be stored
    )
    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private Set<Ingredient> ingredients = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    // One recipe will have only one associated notes record, when we delete the recipe also delete the notes
    private Notes notes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCookTimeMins() {
        return cookTimeMins;
    }

    public void setCookTimeMins(Integer cookTimeMins) {
        this.cookTimeMins = cookTimeMins;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public Integer getPrepTimeMins() {
        return prepTimeMins;
    }

    public void setPrepTimeMins(Integer prepTimeMins) {
        this.prepTimeMins = prepTimeMins;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Notes getNotes() {
        return notes;
    }

    public void setNotes(Notes notes) {
        this.notes = notes;
    }
}
