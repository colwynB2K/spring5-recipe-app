package guru.springframework.spring5recipeapp.bootstrap;

import guru.springframework.spring5recipeapp.domain.Category;
import guru.springframework.spring5recipeapp.domain.UnitOfMeasure;
import guru.springframework.spring5recipeapp.repository.CategoryRepository;
import guru.springframework.spring5recipeapp.repository.RecipeRepository;
import guru.springframework.spring5recipeapp.repository.UnitOfMeasureRepository;
import guru.springframework.spring5recipeapp.service.CategoryService;
import guru.springframework.spring5recipeapp.service.RecipeService;
import guru.springframework.spring5recipeapp.service.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

// Use this for adding reference data (fundamental data needed to be able to use the application, like categories and UOMs) to database
@Component
@Slf4j
@Profile({"dev", "prod"})
public class MySqlDataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final CategoryService categoryService;
    private final RecipeService recipeService;
    private final UnitOfMeasureService unitOfMeasureService;

    @Autowired
    public MySqlDataInitializer(CategoryRepository categoryRepository,
                                RecipeRepository recipeRepository,
                                UnitOfMeasureRepository unitOfMeasureRepository,
                                CategoryService categoryService,
                                RecipeService recipeService,
                                UnitOfMeasureService unitOfMeasureService) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryService = categoryService;
        this.recipeService = recipeService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (categoryRepository.count() == 0) {
            log.info("Loading Categories...");
            loadCategories();
        }

        if (unitOfMeasureRepository.count() == 0) {
            log.info("Loading UOMs...");
            loadUOMs();
        }
    }

    private void loadCategories() {
        saveCategory("American");
        saveCategory("Fast Food");
        saveCategory("Good Belgian Cooking");
        saveCategory("Italian");
        saveCategory("'Mexican");
    }

    private void loadUOMs() {
        saveUOM("Cup", "Cup");
        saveUOM("Dash", "Dash");
        saveUOM("Drop", "Drop");
        saveUOM("Gram", "g");
        saveUOM("Kilogram", "kg");
        saveUOM("Milliliter", "ml");
        saveUOM("Liter", "l");
        saveUOM("Ounce", "oz");
        saveUOM("Pinch", "Pinch");
        saveUOM("Tablespoon", "Tablespoon");
        saveUOM("Teaspoon'", "Teaspoon'");
    }

    private void saveCategory(String categoryName) {
        Category cat = new Category();
        cat.setName(categoryName);
        categoryRepository.save(cat);
    }

    private void saveUOM(String uomName, String unit) {
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setName(uomName);
        uom.setUnit(unit);
        unitOfMeasureRepository.save(uom);
    }
}
