package guru.springframework.spring5recipeapp.controller;

import guru.springframework.spring5recipeapp.domain.Category;
import guru.springframework.spring5recipeapp.domain.UnitOfMeasure;
import guru.springframework.spring5recipeapp.repository.CategoryRepository;
import guru.springframework.spring5recipeapp.repository.UnitOfMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String getIndexPage() {
        return "index";
    }

}
