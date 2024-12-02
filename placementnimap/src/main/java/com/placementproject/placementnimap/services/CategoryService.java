package com.placementproject.placementnimap.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import com.placementproject.placementnimap.model.Category;
import com.placementproject.placementnimap.repository.CategoryRepository;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository cr;

    public Page<Category> findAllCat(int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("category_id"));
        Page<Category> result = cr.findAll(pageable);
        if (result.isEmpty()) {
    throw new RuntimeException("No categories found!");
}
       System.out.println("Page content: " + result.getContent());
       return result;
    }


    public Optional<Category> findByID(long id){
            return cr.findById(id);
    }

    public String addCategory(Category category){
        cr.save(category);
        return "Added Category";
    }

    public Category updateCategory(long categoryId, Category category){
        Category existingCategory = cr.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found with id " + categoryId));
        
        existingCategory.setCategoryName(category.getCategoryName());

        return cr.save(existingCategory);
    }

    public void deleteCategory(long id)
    {
        cr.findById(id).orElseThrow(() -> new RuntimeException("Category not found with id " + id));
        cr.deleteById(id);
        System.out.println("Deleted category id :"+id);
    }


}
