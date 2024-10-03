package com.kindsonthegenius.inventoryms_springboot_api.repositories;

import com.kindsonthegenius.inventoryms_springboot_api.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

    @Query(value = "select c from Country c where " +
            "c.description LIKE %?1% or c.capital like %?1% or c.continent like %?1%")
    List<Country> findByKeyword1(String keyword);

    @Query(value = "select c from Country c where " +
            "concat(c.description, c.capital, c.code, c.continent, c.nationality) like %?1%")
    List<Country> findByKeyword(String keyword);

}
