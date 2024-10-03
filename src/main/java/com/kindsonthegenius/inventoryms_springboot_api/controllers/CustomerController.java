package com.kindsonthegenius.inventoryms_springboot_api.controllers;

import com.kindsonthegenius.inventoryms_springboot_api.models.Customer;
import com.kindsonthegenius.inventoryms_springboot_api.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public List<Customer> getCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable("id") Long id){
        return customerService.getCustomerById(id);
    }

    @PutMapping("/customer/{id}")
    public Customer updateCustomer(@RequestBody() Customer customer, @PathVariable("id") Long id){
        return customerService.save(customer);
    }

    @PostMapping("/customers")
    public Customer addNew(@RequestBody() Customer customer){
        return customerService.save(customer);
    }

    @DeleteMapping("/customer/{id}")
    public void deleteCustomer(@PathVariable("id") Long id){
        customerService.deleteCustomer(id);
    }

}
