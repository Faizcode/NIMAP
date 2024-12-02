package com.placementproject.placementnimap.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.placementproject.placementnimap.model.Product;
import com.placementproject.placementnimap.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {
    
    @Autowired
    private ProductRepository pr;

   
    public List<Product> gettingAllProduct(int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        List<Product> resultProduct = pr.findAll(pageable).getContent();
        if(resultProduct.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Product Found on this page kindly change the page");
        }
        return resultProduct;
    }

    public Product gettingProductById(long id){
        return pr.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The Product data is not found for id: "+id)); 
    }

    public Product addingProduct(Product product){
        return pr.save(product);
    }

    public Product updatingProduct(long id, Product product){
        Product existingProduct = pr.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id :" + id)); 
        existingProduct.setProductName(product.getProductName());
        existingProduct.setProductPrice(product.getProductPrice());
        existingProduct.setCategory(product.getCategory());

        return pr.save(existingProduct);
    }

    public String deletingProduct(long id)
    {
        pr.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id: " + id));
        pr.deleteById(id);
        return "Product Deleted Successfully Using Id";
    }
}
