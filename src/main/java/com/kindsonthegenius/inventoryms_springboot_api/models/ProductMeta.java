package com.kindsonthegenius.inventoryms_springboot_api.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import jakarta.persistence.*;

@Entity
@Table(name = "product_meta", uniqueConstraints = @UniqueConstraint(columnNames = {"productId", "key"}))
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ProductMeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "productid", insertable = false, updatable = false)
//    private Product product;
//    private Long productid;

    @Column(name="`key`", nullable = false, length = 50)
    private String key;

    @Column(columnDefinition = "TEXT")
    private String content;

    // Getters and Setters
}
