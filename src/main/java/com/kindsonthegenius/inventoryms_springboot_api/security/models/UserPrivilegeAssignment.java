package com.kindsonthegenius.inventoryms_springboot_api.security.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.kindsonthegenius.inventoryms_springboot_api.models.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "user_privilege_assignment",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"userid", "privilegeid"})})
public class UserPrivilegeAssignment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userid", insertable = false, updatable = false)
    private User user;
    private Long userid;

    @ManyToOne
    @JoinColumn(name = "privilegeid", insertable = false, updatable = false)
    private Privilege privilege;
    private Long privilegeid;

    public UserPrivilegeAssignment(Long userid, Long privilegeid) {
        this.userid = userid;
        this.privilegeid = privilegeid;
    }
}
