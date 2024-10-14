package com.kindsonthegenius.inventoryms_springboot_api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kindsonthegenius.inventoryms_springboot_api.security.models.Auditable;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "`order`")
@Data
public class Order extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Column(nullable = false)
    private Float subTotal = 0.0f;

    @Column(nullable = false)
    private Float itemDiscount = 0.0f;

    @Column(nullable = false)
    private Float tax = 0.0f;

    @Column(nullable = false)
    private Float shipping = 0.0f;

    @Column(nullable = false)
    private Float total = 0.0f;

    @Column(length = 50)
    private String promo;

    @Column(nullable = false)
    private Float discount = 0.0f;

    @Column(nullable = false)
    private Float grandTotal = 0.0f;


    @OneToMany(mappedBy = "order")
    @JsonIgnore
    private List<OrderItem> orderItems;

    @OneToMany(mappedBy = "order")
    @JsonIgnore
    private List<Transaction> transactions;

    @Column(columnDefinition = "TEXT")
    private String remarks;

    @ManyToOne
    @JoinColumn(name = "ordertypeid", insertable = false, updatable = false)
    private CommonObject orderType;
    private Long ordertypeid;

    @ManyToOne
    @JoinColumn(name = "orderstatusid", insertable = false, updatable = false)
    private CommonObject orderStatus;
    private Long orderstatusid;

    @ManyToOne
    @JoinColumn(name = "supplierid", insertable = false, updatable = false)
    private Supplier supplier;
    private Long supplierid;

    @ManyToOne
    @JoinColumn(name = "customerid", insertable = false, updatable = false)
    private Customer customer;
    private Long customerid;

}
