package com.kindsonthegenius.inventoryms_springboot_api.services;

import com.kindsonthegenius.inventoryms_springboot_api.models.State;
import com.kindsonthegenius.inventoryms_springboot_api.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {

	@Autowired
	private StateRepository stateRepository;
	
	//Get All States
	public List<State> findAll(){
		return stateRepository.findAll();
	}	
	
	//Get State By Id
	public State findById(int id) {
		return stateRepository.findById(id).orElse(null);
	}	
	
	//Delete State
	public void delete(int id) {
		stateRepository.deleteById(id);
	}
	
	//Update State
	public State save( State state) {
		return stateRepository.save(state);
	}
}
