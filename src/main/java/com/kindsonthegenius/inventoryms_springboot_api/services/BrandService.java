package com.kindsonthegenius.inventoryms_springboot_api.services;


import com.kindsonthegenius.inventoryms_springboot_api.models.Brand;
import com.kindsonthegenius.inventoryms_springboot_api.models.BrandStats;
import com.kindsonthegenius.inventoryms_springboot_api.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class BrandService {

    private BrandRepository brandRepository;

    @Autowired
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<Brand> getAllBrands(){
        return brandRepository.findAll();
    }

    public Brand getBrandById(Long id) {
        return brandRepository.findById(id).orElse(null);
    }

    public Brand save(Brand brand) {
        return brandRepository.save(brand);
    }

    public void deleteBrand(Long id){
        brandRepository.deleteById(id);
    }

    public BrandStats getBrandStats() {
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

        Long currentMonthCount = brandRepository.countBrandsAddedInCurrentMonth(currentMonth, currentYear);
        Long previousMonthCount = (previousMonth == 12 && previousYear < currentYear) ?
                brandRepository.countBrandsAddedInDecemberOfPreviousYear(previousYear) :
                brandRepository.countBrandsAddedInPreviousMonth(previousMonth, previousYear);

        double percentageDifference = 0;
        if (previousMonthCount > 0) {
            percentageDifference = ((double) (currentMonthCount - previousMonthCount) / previousMonthCount) * 100;
        } else if (currentMonthCount > 0) {
            percentageDifference = 100;
        }

        return new BrandStats(currentMonthCount, previousMonthCount, percentageDifference);
    }
}
