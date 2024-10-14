package com.kindsonthegenius.inventoryms_springboot_api.models;

import com.kindsonthegenius.inventoryms_springboot_api.security.models.Auditable;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "brand")
@Data
public class Brand extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 75)
    private String title;

    @Column(columnDefinition = "TINYTEXT")
    private String summary;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(columnDefinition = "TEXT")
    private String content;

    // Getters and Setters
}
