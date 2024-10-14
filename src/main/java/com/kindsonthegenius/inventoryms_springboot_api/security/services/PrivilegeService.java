package com.kindsonthegenius.inventoryms_springboot_api.security.services;

import com.kindsonthegenius.inventoryms_springboot_api.repositories.UserRepository;
import com.kindsonthegenius.inventoryms_springboot_api.security.models.Privilege;
import com.kindsonthegenius.inventoryms_springboot_api.security.repositories.PrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrivilegeService {

    @Autowired
    private PrivilegeRepository privilegeRepository;

    public List<Privilege> findAll(){
        return privilegeRepository.findAll();
    }

    public Privilege getPrivilege(int id){
        return privilegeRepository.findById((long) id).orElse(null);
    }

    public  Privilege save(Privilege privilege){
        return privilegeRepository.save(privilege);
    }

    public void delete(Integer id) {
        privilegeRepository.deleteById(Long.valueOf(id));
    }

    public Privilege getById(Integer id) {
        return privilegeRepository.findById(Long.valueOf(id)).orElse(null);
    }

    public void update(Privilege privilege) {
        privilegeRepository.save(privilege);
    }

}
