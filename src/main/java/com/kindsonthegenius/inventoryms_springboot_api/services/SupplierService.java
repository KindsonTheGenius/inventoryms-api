package com.kindsonthegenius.inventoryms_springboot_api.services;

import com.kindsonthegenius.inventoryms_springboot_api.models.Supplier;
import com.kindsonthegenius.inventoryms_springboot_api.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {
	@Autowired
	private SupplierRepository supplierRepository;

	//Get All Suppliers
	public List<Supplier> findAll(){
		return supplierRepository.findAll();
	}

	//Get Supplier By Id
	public Supplier findById(Long id) {
		return supplierRepository.findById(id).orElse(null);
	}

	//Delete Supplier
	public void deleteById(Long id) {
		supplierRepository.deleteById(id);
	}

	//Update Supplier
	public Supplier save(Supplier supplier) {
		return supplierRepository.save(supplier);
	}

}
