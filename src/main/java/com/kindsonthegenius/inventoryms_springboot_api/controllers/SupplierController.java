package com.kindsonthegenius.inventoryms_springboot_api.controllers;

import com.kindsonthegenius.inventoryms_springboot_api.models.Supplier;
import com.kindsonthegenius.inventoryms_springboot_api.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SupplierController {
	private final SupplierService supplierService;

	@Autowired
	public SupplierController(SupplierService supplierService) {
		this.supplierService = supplierService;
	}

	@GetMapping("/suppliers")
	public List<Supplier> getSuppliers(){
		return supplierService.findAll();
	}

	@GetMapping("/supplier/{id}")
	public Supplier getSupplier(@PathVariable("id") Long id){
		return supplierService.findById(id);
	}

	@PutMapping("/supplier/{id}")
	public Supplier updateSupplier(@RequestBody() Supplier supplier, @PathVariable("id") Long id){
		return supplierService.save(supplier);
	}

	@PostMapping("/suppliers")
	public Supplier addNew(@RequestBody() Supplier supplier){
		return supplierService.save(supplier);
	}

	@DeleteMapping("/supplier/{id}")
	public void deleteSupplier(@PathVariable("id") Long id){
		supplierService.deleteById(id);
	}
}
