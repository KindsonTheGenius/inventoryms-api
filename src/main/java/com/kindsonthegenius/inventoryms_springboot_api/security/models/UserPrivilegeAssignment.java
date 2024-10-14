package com.kindsonthegenius.inventoryms_springboot_api.security.models;

import com.kindsonthegenius.inventoryms_springboot_api.models.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UserPrivilegeAssignment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

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
