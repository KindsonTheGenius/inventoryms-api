package com.kindsonthegenius.inventoryms_springboot_api.security.models;

import com.kindsonthegenius.inventoryms_springboot_api.models.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Privilege extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToOne
    @JoinColumn(name = "userid", insertable = false, updatable = false)
    private User user;
    private Long userid;

    @ManyToOne
    @JoinColumn(name = "roleid", insertable = false, updatable = false)
    private Role role;
    private Long roleid;

    @OneToMany(mappedBy = "privilege")
    private List<UserPrivilegeAssignment> users;
}
