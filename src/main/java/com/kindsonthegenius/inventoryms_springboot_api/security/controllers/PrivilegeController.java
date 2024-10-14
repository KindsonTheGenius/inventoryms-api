package com.kindsonthegenius.inventoryms_springboot_api.security.controllers;

import com.kindsonthegenius.inventoryms_springboot_api.security.models.Privilege;
import com.kindsonthegenius.inventoryms_springboot_api.security.services.PrivilegeService;
import com.kindsonthegenius.inventoryms_springboot_api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PrivilegeController {


    @Autowired
    private PrivilegeService privilegeService;

    @Autowired
    private UserService userService;

    @GetMapping("/privileges")
    public List<Privilege> parameters() {
        return privilegeService.findAll();
    }

    @GetMapping("/privilege/{id}")
    public Privilege getById(@PathVariable Integer id) {
        return privilegeService.getById(id);
    }

    @PutMapping("/privilege/{id}")
    public Privilege updatePrivilege(@RequestBody() Privilege privilege, @PathVariable("id") Long id){
        return privilegeService.save(privilege);
    }

    @PostMapping("/privileges")
    public Privilege save(Privilege privilege) {
        return privilegeService.save(privilege);
    }

    @DeleteMapping("/privilege/delete/{id}")
    public void delete(@PathVariable Integer id) {
        privilegeService.delete(id);
    }
}
