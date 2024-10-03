package com.kindsonthegenius.inventoryms_springboot_api.repositories;

import com.kindsonthegenius.inventoryms_springboot_api.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Total categories added in the current month
    @Query("SELECT COUNT(c) FROM Category c WHERE FUNCTION('MONTH', c.createdDate) = :currentMonth AND FUNCTION('YEAR', c.createdDate) = :currentYear")
    Long countCategoriesAddedInCurrentMonth(@Param("currentMonth") int currentMonth, @Param("currentYear") int currentYear);

    // Total categories added in the previous month
    @Query("SELECT COUNT(c) FROM Category c WHERE FUNCTION('MONTH', c.createdDate) = :previousMonth AND FUNCTION('YEAR', c.createdDate) = :currentYear")
    Long countCategoriesAddedInPreviousMonth(@Param("previousMonth") int previousMonth, @Param("currentYear") int currentYear);

    // Total categories added in December of the previous year if the current month is January
    @Query("SELECT COUNT(c) FROM Category c WHERE FUNCTION('MONTH', c.createdDate) = 12 AND FUNCTION('YEAR', c.createdDate) = :previousYear")
    Long countCategoriesAddedInDecemberOfPreviousYear(@Param("previousYear") int previousYear);

}
