package com.kindsonthegenius.inventoryms_springboot_api.services;

import com.kindsonthegenius.inventoryms_springboot_api.models.Product;
import com.kindsonthegenius.inventoryms_springboot_api.models.ProductStats;
import com.kindsonthegenius.inventoryms_springboot_api.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    public ProductStats getProductStats() {
        Calendar cal = Calendar.getInstance();
        int currentMonth = cal.get(Calendar.MONTH) + 1; // Calendar.MONTH is zero-based
        int currentYear = cal.get(Calendar.YEAR);

        int previousMonth, previousYear;

        if (currentMonth == 1) {
            // If it's January, compare with December of the previous year
            previousMonth = 12;
            previousYear = currentYear - 1;
        } else {
            // Otherwise, compare with the previous month in the current year
            previousMonth = currentMonth - 1;
            previousYear = currentYear;
        }

        // Fetch the count of products added in the current and previous months
        Long currentMonthCount = productRepository.countProductsAddedInCurrentMonth(currentMonth, currentYear);
        Long previousMonthCount = (previousMonth == 12 && previousYear < currentYear) ?
                productRepository.countProductsAddedInDecemberOfPreviousYear(previousYear) :
                productRepository.countProductsAddedInPreviousMonth(previousMonth, previousYear);

        // Calculate percentage difference
        double percentageDifference = 0;
        if (previousMonthCount > 0) {
            percentageDifference = ((double) (currentMonthCount - previousMonthCount) / previousMonthCount) * 100;
        } else if (currentMonthCount > 0) {
            percentageDifference = 100;  // If there were no products in the previous month but some in the current month
        }

        // Create and return a ProductStats object (create this class as needed)
        return new ProductStats(currentMonthCount, previousMonthCount, percentageDifference);
    }
}
