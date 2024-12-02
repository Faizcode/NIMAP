package com.placementproject.placementnimap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Category>> gettingAllCategoryFromDatabase(@RequestParam(defaultValue="3") int page, @RequestParam(defaultValue = "2") int size) {
         List<Category> listCategory = cs.findingAllCategpry(page, size);
         return ResponseEntity.ok(listCategory); 
    }

    @GetMapping("/{di}")
    public Category gettingCategoryByIdFromDatabaseOptional(@PathVariable long di)
    {
        return cs.gettingById(di);
    }

    @PostMapping
    public Category addingNewCategoryIntoDatabase(@RequestBody Category category){
        return cs.addingNewCategory(category);
    }

    @PutMapping("/{di}")
    public Category updatingCategoryFromDatabase(@PathVariable long di, @RequestBody Category category){
        return cs.updatingCategory(di, category);
    }

    @DeleteMapping("/{di}")
    public String deletingCategoryByIdFromDatabase(@PathVariable long di){
        return cs.deletingCategoryById(di);
    }
}
