package com.placementproject.placementnimap.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.placementproject.placementnimap.model.Product;
import com.placementproject.placementnimap.repository.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository pr;

   
    public List<Product> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size,Sort.by("productId"));
        Page<Product> listProduct = pr.findAll(pageable);
        return listProduct.getContent();
    }

    public Optional<Product> findByID(long id){
            return pr.findById(id);
        
    }

    public String addProduct(Product product){
        pr.save(product);
        return "product Added";
    }

    public Product updateProduct(long productId, Product product){
        Product existingProduct = pr.findById(productId).orElseThrow(() -> new RuntimeException("Product not found with id " + productId));
        
        existingProduct.setProductName(product.getProductName());

        return pr.save(existingProduct);
    }

    public void deleteProduct(long id)
    {
        pr.findById(id).orElseThrow(() -> new RuntimeException("Product not found with id " + id));
        pr.deleteById(id);
    }
}
