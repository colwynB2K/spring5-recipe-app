package guru.springframework.spring5recipeapp.repository;

import guru.springframework.spring5recipeapp.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

//    @EntityGraph(value = "Category.recipes")
    Optional<Category> findByName(String name);

}
