package com.kindsonthegenius.inventoryms_springboot_api.controllers;

import com.kindsonthegenius.inventoryms_springboot_api.models.SubCategory;
import com.kindsonthegenius.inventoryms_springboot_api.services.SubCategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    public SubCategoryController(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    @GetMapping("/subCategories")
    public List<SubCategory> getSubCategories(){
        return subCategoryService.getAllSubCategories();
    }

    @GetMapping("/subCategory/{id}")
    public SubCategory getSubCategory(@PathVariable("id") Long id){
        return subCategoryService.getSubCategoryById(id);
    }

    @PutMapping("/subCategory/{id}")
    public SubCategory updateSubCategory(@RequestBody() SubCategory subCategory, @PathVariable("id") Long id){
        return subCategoryService.save(subCategory);
    }

    @PostMapping("/subCategories")
    public SubCategory addNew(@RequestBody() SubCategory subCategory){
        return subCategoryService.save(subCategory);
    }

    @DeleteMapping("/subCategory/{id}")
    public void deleteSubCategory(@PathVariable("id") Long id){
        subCategoryService.deleteSubCategory(id);
    }
}
