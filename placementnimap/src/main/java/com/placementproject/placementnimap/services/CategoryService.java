package com.placementproject.placementnimap.services;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.placementproject.placementnimap.model.Category;
import com.placementproject.placementnimap.repository.CategoryRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoryService {
    
    @Autowired
    private CategoryRepository cr;

    public List<Category> findingAllCategpry(int page, int size){
        Pageable pageable = PageRequest.of(page-1, size);
        List<Category> resultCategory = cr.findAll(pageable).getContent();
        if (resultCategory.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No categories found on this page ");
        }
       return resultCategory;
    }


    public Category gettingById(long id){
            Category currentCategory = cr.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No data foubd for this id :"+id));
            return currentCategory;
    }

    public Category addingNewCategory(Category category){
       return cr.save(category);
    }

    public Category updatingCategory(long id, Category category){
        Category existingCategory = cr.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found with id " + id));
        existingCategory.setCategoryName(category.getCategoryName());
        return cr.save(existingCategory);
    }

    public String deletingCategoryById(long id)
    {
        cr.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found with id " + id));
        cr.deleteById(id);
        return "Category Deleted Successfully Using ID";
    }


}
