package guru.springframework.spring5recipeapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = "recipe")
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
}
