package com.kindsonthegenius.inventoryms_springboot_api.repositories;

import com.kindsonthegenius.inventoryms_springboot_api.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // Total orders added in the current month
    @Query("SELECT COUNT(o) FROM Order o WHERE FUNCTION('MONTH', o.createdDate) = :currentMonth AND FUNCTION('YEAR', o.createdDate) = :currentYear")
    Long countOrdersAddedInCurrentMonth(@Param("currentMonth") int currentMonth, @Param("currentYear") int currentYear);

    // Total orders added in the previous month
    @Query("SELECT COUNT(o) FROM Order o WHERE FUNCTION('MONTH', o.createdDate) = :previousMonth AND FUNCTION('YEAR', o.createdDate) = :currentYear")
    Long countOrdersAddedInPreviousMonth(@Param("previousMonth") int previousMonth, @Param("currentYear") int currentYear);

    // Total orders added in December of the previous year if the current month is January
    @Query("SELECT COUNT(o) FROM Order o WHERE FUNCTION('MONTH', o.createdDate) = 12 AND FUNCTION('YEAR', o.createdDate) = :previousYear")
    Long countOrdersAddedInDecemberOfPreviousYear(@Param("previousYear") int previousYear);

}
