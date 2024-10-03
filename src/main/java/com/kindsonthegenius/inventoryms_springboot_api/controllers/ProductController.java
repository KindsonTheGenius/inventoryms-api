package com.kindsonthegenius.inventoryms_springboot_api.controllers;

import com.kindsonthegenius.inventoryms_springboot_api.models.Product;
import com.kindsonthegenius.inventoryms_springboot_api.models.ProductStats;
import com.kindsonthegenius.inventoryms_springboot_api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable("id") Long id){
        return productService.getProductById(id);
    }

    @PutMapping("/product/{id}")
    public Product updateProduct(@RequestBody() Product product, @PathVariable("id") Long id){
        System.out.println(product);
        return productService.updateProduct(product);
    }

    @PostMapping("/products")
    public Product addNew(@RequestBody() Product product){
        System.out.println(product);
        return productService.save(product);
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
    }

    @GetMapping("/product/stats")
    public ResponseEntity<ProductStats> getOrderStats() {
        ProductStats stats = productService.getProductStats();
        return ResponseEntity.ok(stats);
    }
}
