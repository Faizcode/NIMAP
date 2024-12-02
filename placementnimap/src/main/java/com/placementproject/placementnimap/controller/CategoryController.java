package com.placementproject.placementnimap.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.placementproject.placementnimap.model.Category;
import com.placementproject.placementnimap.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    
    @Autowired
    private CategoryService cs;

    @GetMapping
    public Page<Category> allCategories(@RequestParam(defaultValue="3") int page, @RequestParam(defaultValue = "2") int size) {
         return cs.findAllCat(page, size);    

    }

    @GetMapping("/{di}")
    public Optional<Category>  byIdcategories(@PathVariable long di){
        return cs.findByID(di);
    }

    @PostMapping
    public String addNewCategory(@RequestBody Category category){
        return cs.addCategory(category);
    }

    @PutMapping("/{di}")
    public Category updateCategories(@PathVariable long di, @RequestBody Category category){
        return cs.updateCategory(di, category);
    }

    @DeleteMapping("/{di}")
    public void deleteCategories(@PathVariable long di){
        cs.deleteCategory(di);
    }
}
