package guru.springframework.spring5recipeapp.controller;

import guru.springframework.spring5recipeapp.dto.RecipeDTO;
import guru.springframework.spring5recipeapp.service.CategoryService;
import guru.springframework.spring5recipeapp.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/recipes")
public class RecipeController {

    private final CategoryService categoryService;
    private final RecipeService recipeService;

    @Autowired
    public RecipeController(CategoryService categoryService, RecipeService recipeService) {
        this.categoryService = categoryService;
        this.recipeService = recipeService;
    }

    @GetMapping("/{recipeId}")
    public String showRecipeDetail(@PathVariable Long recipeId, Model model) {
        model.addAttribute("recipe", recipeService.findById(recipeId));

        return "recipes/detail";
    }

    @GetMapping({"/edit", "/edit/{recipeId}"})
    public String showRecipeForm(@PathVariable(required = false) Long recipeId, Model model) {
        if (recipeId != null) {
            model.addAttribute("recipe", recipeService.findById(recipeId));
        } else {
            model.addAttribute("recipe", new RecipeDTO());
        }
        model.addAttribute("categories", categoryService.findAll());

        return "recipes/form";
    }

    @PostMapping("")
    public String saveRecipe(@ModelAttribute RecipeDTO recipeDTO) {
        RecipeDTO savedRecipeDTO = recipeService.save(recipeDTO);

        return "redirect:/recipes/" + savedRecipeDTO.getId(); // Use MVC redirect to redirect user to the Recipe Detail page (so the browser should load a new url here)
    }

    @GetMapping("/{recipeId}/delete")
    public String deleteById(@PathVariable Long recipeId) {
        recipeService.deleteById(recipeId);

        return "redirect:/";
    }
}
