package com.kindsonthegenius.inventoryms_springboot_api.security.controllers;

import com.kindsonthegenius.inventoryms_springboot_api.models.User;
import com.kindsonthegenius.inventoryms_springboot_api.security.models.Privilege;
import com.kindsonthegenius.inventoryms_springboot_api.security.models.UserPrivilegeAssignment;
import com.kindsonthegenius.inventoryms_springboot_api.security.services.UserPrivilegeAssignmentService;
import com.kindsonthegenius.inventoryms_springboot_api.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
public class UserPrivilegeAssignmentController {

    @Autowired
    private UserPrivilegeAssignmentService userPrivilegeAssignmentService;

    @GetMapping("/userPrivilegeAssignments")
    public List<UserPrivilegeAssignment> parameters(Model model) {
        return userPrivilegeAssignmentService.findAll();
    }

    @GetMapping("/userPrivilegeAssignment/{id}")
    public UserPrivilegeAssignment getById(@PathVariable Long id) {
        return userPrivilegeAssignmentService.getById(id);
    }

    @PostMapping("/userPrivilegeAssignments")
    public UserPrivilegeAssignment save(@RequestBody UserPrivilegeAssignment userPrivilegeAssignment) {
        return userPrivilegeAssignmentService.save(userPrivilegeAssignment);
    }

    @DeleteMapping("/userPrivilegeAssignment/{id}")
    public void deleteUserPrivilegeAssignment(@PathVariable("id") Long id){
        userPrivilegeAssignmentService.delete(id);
    }

    @Transactional
    @PostMapping("/user/{userid}/privileges")
    public ResponseEntity<String> savePrivileges(@PathVariable("userid") Long userid, @RequestBody List<Privilege> privileges) {
       try {
           List<Privilege> savedPrivileges = userPrivilegeAssignmentService.savePrivileges(
                   privileges, userid
           );
           return ResponseEntity.status(HttpStatus.CREATED).body("Privileges were saved successfully");
       } catch (Exception ex) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + ex.getMessage());
       }
    }

    @GetMapping("/user/{userid}/privileges")
    public  List<Privilege> getUserPrivileges(@PathVariable("userid") Long userid) {
        return userPrivilegeAssignmentService.getUserPrivileges(userid);
    }

    @GetMapping("/privilege/{privilegeid}/users")
    public  List<User> getUsersByPrivilege(@PathVariable("privilegeid") Long privilegeid) {
        return userPrivilegeAssignmentService.getUsersByPrivilege(privilegeid);
    }

    @DeleteMapping("/user/{userid}/privileges/clear")
    public ResponseEntity<String> clearUserPrivileges(@PathVariable("userid") Long userid) {
        try {
            userPrivilegeAssignmentService.deletePrivileges(userid);
            return ResponseEntity.status(HttpStatus.CREATED).body("Privileges were cleared successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + ex.getMessage());
        }
    }

}
