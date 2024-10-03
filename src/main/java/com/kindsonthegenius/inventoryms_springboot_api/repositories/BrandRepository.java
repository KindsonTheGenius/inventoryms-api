package com.kindsonthegenius.inventoryms_springboot_api.repositories;

import com.kindsonthegenius.inventoryms_springboot_api.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    // Total brands added in the current month
    @Query("SELECT COUNT(b) FROM Brand b WHERE FUNCTION('MONTH', b.createdDate) = :currentMonth AND FUNCTION('YEAR', b.createdDate) = :currentYear")
    Long countBrandsAddedInCurrentMonth(@Param("currentMonth") int currentMonth, @Param("currentYear") int currentYear);

    // Total brands added in the previous month
    @Query("SELECT COUNT(b) FROM Brand b WHERE FUNCTION('MONTH', b.createdDate) = :previousMonth AND FUNCTION('YEAR', b.createdDate) = :currentYear")
    Long countBrandsAddedInPreviousMonth(@Param("previousMonth") int previousMonth, @Param("currentYear") int currentYear);

    // Total brands added in December of the previous year if the current month is January
    @Query("SELECT COUNT(b) FROM Brand b WHERE FUNCTION('MONTH', b.createdDate) = 12 AND FUNCTION('YEAR', b.createdDate) = :previousYear")
    Long countBrandsAddedInDecemberOfPreviousYear(@Param("previousYear") int previousYear);

}
