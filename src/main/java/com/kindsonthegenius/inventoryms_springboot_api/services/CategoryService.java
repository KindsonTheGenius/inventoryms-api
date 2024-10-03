package com.kindsonthegenius.inventoryms_springboot_api.services;


import com.kindsonthegenius.inventoryms_springboot_api.models.Category;
import com.kindsonthegenius.inventoryms_springboot_api.models.CategoryStats;
import com.kindsonthegenius.inventoryms_springboot_api.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }

    public CategoryStats getCategoryStats() {
        Calendar cal = Calendar.getInstance();
        int currentMonth = cal.get(Calendar.MONTH) + 1;
        int currentYear = cal.get(Calendar.YEAR);

        int previousMonth, previousYear;

        if (currentMonth == 1) {
            previousMonth = 12;
            previousYear = currentYear - 1;
        } else {
            previousMonth = currentMonth - 1;
            previousYear = currentYear;
        }

        Long currentMonthCount = categoryRepository.countCategoriesAddedInCurrentMonth(currentMonth, currentYear);
        Long previousMonthCount = (previousMonth == 12 && previousYear < currentYear) ?
                categoryRepository.countCategoriesAddedInDecemberOfPreviousYear(previousYear) :
                categoryRepository.countCategoriesAddedInPreviousMonth(previousMonth, previousYear);

        double percentageDifference = 0;
        if (previousMonthCount > 0) {
            percentageDifference = ((double) (currentMonthCount - previousMonthCount) / previousMonthCount) * 100;
        } else if (currentMonthCount > 0) {
            percentageDifference = 100;
        }

        return new CategoryStats(currentMonthCount, previousMonthCount, percentageDifference);
    }
}
