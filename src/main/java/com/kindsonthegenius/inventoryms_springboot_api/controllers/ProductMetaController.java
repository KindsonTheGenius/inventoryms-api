package com.kindsonthegenius.inventoryms_springboot_api.controllers;

import com.kindsonthegenius.inventoryms_springboot_api.models.ProductMeta;
import com.kindsonthegenius.inventoryms_springboot_api.services.ProductMetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductMetaController {

    private ProductMetaService productMetaService;

    @Autowired
    public ProductMetaController(ProductMetaService productMetaService){
        this.productMetaService = productMetaService;
    }


    @GetMapping("/productMetas")
    public List<ProductMeta> getProductMetaes(){
        return productMetaService.getAllProductMetas();
    }

    @GetMapping("/productMeta/{id}")
    public ProductMeta getProductMeta(@PathVariable("id") Long id){
        return productMetaService.getProductMetaById(id);
    }

    @PutMapping("/productMeta/{id}")
    public ProductMeta updateProductMeta(@RequestBody() ProductMeta productMeta, @PathVariable("id") Long id){
        return productMetaService.save(productMeta);
    }

    @PostMapping("/productMetas")
    public ProductMeta addNew(@RequestBody() ProductMeta productMeta){
        return productMetaService.save(productMeta);
    }

    @DeleteMapping("/productMeta/{id}")
    public void deleteProductMeta(@PathVariable("id") Long id){
        productMetaService.deleteProductMeta(id);
    }
}
