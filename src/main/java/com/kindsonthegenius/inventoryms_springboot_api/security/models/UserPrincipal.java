package com.kindsonthegenius.inventoryms_springboot_api.security.models;

import com.kindsonthegenius.inventoryms_springboot_api.models.User;
import com.kindsonthegenius.inventoryms_springboot_api.security.services.UserPrivilegeAssignmentService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {

    private final  UserPrivilegeAssignmentService assignmentService;
    private final User user;

    public UserPrincipal(UserPrivilegeAssignmentService assignmentService, User user) {
        this.assignmentService = assignmentService;
        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Privilege> privilegeList = assignmentService.getUserPrivileges(user.getId());

        return privilegeList.stream()
                .map(privilege -> new SimpleGrantedAuthority(privilege.getDescription()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isAccountVerified();
    }
}
