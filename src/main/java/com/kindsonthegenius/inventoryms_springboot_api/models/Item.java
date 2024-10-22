package com.kindsonthegenius.inventoryms_springboot_api.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.kindsonthegenius.inventoryms_springboot_api.security.models.Auditable;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "item")
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Item extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToOne
    @JoinColumn(name = "productid", insertable = false, updatable = false)
    private Product product;
    private Long productid;

    @ManyToOne()
    @JoinColumn(name = "brandid", insertable = false, updatable = false)
    private Brand brand;
    private Long brandid;

    @ManyToOne
    @JoinColumn(name = "supplierid", insertable = false, updatable = false)
    private Supplier supplier;
    private Long supplierid;

    @Column(length = 100)
    private String sku;

    private Float mrp = 0.0f;

    private Float discount = 0.0f;

    private Float price = 0.0f;

    private Short quantity = 0;

    private Short reorder_level;

    private Short sold = 0;

    private Short available = 0;

    private Short defective = 0;

    public void setQuantity(Short quantity) {
        if(quantity < reorder_level) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.quantity = quantity;
    }
}
