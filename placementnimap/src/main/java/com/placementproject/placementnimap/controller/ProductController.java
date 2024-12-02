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

import com.placementproject.placementnimap.model.Product;
import com.placementproject.placementnimap.services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    
     @Autowired
    private ProductService ps;

    @GetMapping
    public List<Product> allProducts(@RequestParam(defaultValue = "2") int page,
    @RequestParam(defaultValue= "10") int size) {
        return ps.findAll(page, size);  // This returns paginated data
    }

    @GetMapping("/{di}")
    public Optional<Product>  byIdProdcuts(@PathVariable long di){
        return ps.findByID(di);
    }

    @PostMapping
    public String addNewProducts(@RequestBody Product product){
        return ps.addProduct(product);
    }

    @PutMapping("/{di}")
    public Product updateProducts(@PathVariable long di, @RequestBody Product product){
        return ps.updateProduct(di, product);
    }

    @DeleteMapping("/{di}")
    public void deleteProducts(@PathVariable long di){
        ps.deleteProduct(di);
    }
}
