package com.kindsonthegenius.inventoryms_springboot_api.security.models;

import com.kindsonthegenius.inventoryms_springboot_api.models.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class SecureToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String token;

    @Column(updatable = false)
    @Basic(optional = false)
    private LocalDateTime expiredAt;

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "id")
    private User user;

    public boolean isExpired(){
        return getExpiredAt().isBefore(LocalDateTime.now());
    }
}
