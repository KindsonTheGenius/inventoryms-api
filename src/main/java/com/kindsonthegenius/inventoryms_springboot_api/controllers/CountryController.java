package com.kindsonthegenius.inventoryms_springboot_api.controllers;

import com.kindsonthegenius.inventoryms_springboot_api.models.Country;
import com.kindsonthegenius.inventoryms_springboot_api.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping("/countries")
    public List<Country> getAll(){
        return countryService.findAll();
    }

    //The Get Country By Id
    @GetMapping("/country/{id}")
    public Country getCountry(@PathVariable Integer id){
        return countryService.getById(id);
    }

    @PutMapping("/country/{id}")
    public Country editCountry(@RequestBody Country country, @PathVariable String id){
        return countryService.save(country);
    }

    @PostMapping("/countries")
    public Country addCountry(@RequestBody Country country){
        return countryService.save(country);
    }

    @DeleteMapping("/country/{id}")
    public  void delete(@PathVariable Integer id){
          countryService.delete(id);
    }

}
