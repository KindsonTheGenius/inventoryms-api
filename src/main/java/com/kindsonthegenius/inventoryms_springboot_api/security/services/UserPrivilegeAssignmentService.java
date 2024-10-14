package com.kindsonthegenius.inventoryms_springboot_api.security.services;

import com.kindsonthegenius.inventoryms_springboot_api.models.User;
import com.kindsonthegenius.inventoryms_springboot_api.security.models.Privilege;
import com.kindsonthegenius.inventoryms_springboot_api.security.models.UserPrivilegeAssignment;
import com.kindsonthegenius.inventoryms_springboot_api.security.repositories.UserPrivilegeAssignmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserPrivilegeAssignmentService {
    @Autowired
    public UserPrivilegeAssignmentRepository repository;

    public List<UserPrivilegeAssignment> findAll() {
        return repository.findAll();
    }

    public UserPrivilegeAssignment getPrivilege(Long id) {
        return repository.findById(id).orElse(null);
    }

    public UserPrivilegeAssignment save(UserPrivilegeAssignment userPrivilegeAssignment) {
        return repository.save(userPrivilegeAssignment);
    }

    public void delete(Long id) {
        repository.deleteById(Long.valueOf(id));
    }

    public UserPrivilegeAssignment getById(Long id) {
        return repository.findById(Long.valueOf(id)).orElse(null);
    }

    public void update(UserPrivilegeAssignment userPrivilegeAssignment) {
        repository.save(userPrivilegeAssignment);
    }

    @Transactional
    public List<Privilege> savePrivileges(List<Privilege> privileges, Long userid) {
        repository.deleteByUserid(userid);

        List<UserPrivilegeAssignment> assignments = privileges.stream()
                .map(privilege -> new UserPrivilegeAssignment(userid, privilege.getId()))
                .toList();

        return repository.saveAll(assignments).stream()
                .map(UserPrivilegeAssignment::getPrivilege)
                .toList();
    }

    public void deletePrivileges(Long userid) {
        repository.deleteByUserid(userid);
    }

    public List<Privilege> getUserPrivileges(Long userid) {
        List<UserPrivilegeAssignment> assignments = repository.findByUserid(userid);
        return assignments.stream()
                .map(UserPrivilegeAssignment::getPrivilege)
                .toList();
    }

    public List<User> getUsersByPrivilege(Long privilegeid) {
        List<UserPrivilegeAssignment> assignments = repository.findByUserid(privilegeid);
        return assignments.stream()
                .map(UserPrivilegeAssignment::getUser)
                .toList();
    }
}
