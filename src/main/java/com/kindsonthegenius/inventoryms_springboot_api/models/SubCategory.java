package com.kindsonthegenius.inventoryms_springboot_api.models;

import com.fasterxml.jackson.annotation.*;
import com.kindsonthegenius.inventoryms_springboot_api.security.models.Auditable;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "sub_category")
@Data
@JsonIgnoreProperties()
public class SubCategory extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "categoryid", insertable = false, updatable = false)
    private Category category;
    private Long categoryid;

}
