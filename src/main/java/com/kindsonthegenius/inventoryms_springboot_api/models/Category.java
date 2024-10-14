package com.kindsonthegenius.inventoryms_springboot_api.models;

import com.fasterxml.jackson.annotation.*;
import com.kindsonthegenius.inventoryms_springboot_api.security.models.Auditable;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "category")
@Data
public class Category extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 75)
    private String title;

    @Column(length = 100)
    private String metaTitle;

    @Column(nullable = false, length = 100)
    private String slug;

    @Column(columnDefinition = "TEXT")
    private String content;

    @OneToMany(mappedBy = "category")
    @JsonManagedReference
    @JsonIgnore
    private List<SubCategory> subCategories;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Product> products;

}
