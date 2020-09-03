package guru.springframework.spring5recipeapp.domain;

import javax.persistence.*;

@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Count on the underlying persistence framework to generate an Id value from a sequence
    private Long id;

    @Lob
    // Large Object annotation: JPA and Hibernate will only support 255 characters,
    // this is allows for more (databases will support Clob - character, Blob - binary Large Objects) => because it is a String, this will become a CLOB in the database
    private String notes;

    @OneToOne                                               // When deleting notes, don't delete the associated recipe
    private Recipe recipe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
