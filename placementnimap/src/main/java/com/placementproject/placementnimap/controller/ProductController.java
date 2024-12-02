package com.placementproject.placementnimap.controller;

import java.util.List;
import java.util.Optional;

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

import com.placementproject.placementnimap.model.Product;
import com.placementproject.placementnimap.services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    
     @Autowired
    private ProductService ps;

    @GetMapping
    public ResponseEntity<List<Product>> gettingAllProductFromDatabase(@RequestParam(defaultValue = "2") int page,
    @RequestParam(defaultValue= "2") int size) {
        List<Product> listProduct = ps.gettingAllProduct(page, size);
        return ResponseEntity.ok(listProduct);
    }

    @GetMapping("/{di}")
    public Product gettingProductByIdFromDatabase(@PathVariable long di){
        return ps.gettingProductById(di);
    }

    @PostMapping
    public Product addingNewProductIntoDatabase(@RequestBody Product product){
        return ps.addingProduct(product);
    }

    @PutMapping("/{di}")
    public Product updatingProductIntoDatabase(@PathVariable long di, @RequestBody Product product){
        return ps.updatingProduct(di, product);
    }

    @DeleteMapping("/{di}")
    public String deletingProductFromDatabase(@PathVariable long di){
       return ps.deletingProduct(di);
    }
}
