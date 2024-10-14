package com.kindsonthegenius.inventoryms_springboot_api.security.models;

import com.fasterxml.jackson.annotation.*;
import com.kindsonthegenius.inventoryms_springboot_api.models.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Privilege extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToOne
    @JoinColumn(name = "roleid", insertable = false, updatable = false)
    @JsonBackReference
    private Role role;
    private Long roleid;

    @OneToMany(mappedBy = "privilege")
    @JsonIgnore
    private List<UserPrivilegeAssignment> users;
}
