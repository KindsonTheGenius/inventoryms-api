package com.kindsonthegenius.inventoryms_springboot_api.models;

import com.fasterxml.jackson.annotation.*;
import com.kindsonthegenius.inventoryms_springboot_api.security.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "projects")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Project extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

//    @OneToMany(mappedBy = "project")
//    @JsonManagedReference
//    @JsonIgnore
//    private List<Task> tasks;

    @ManyToOne
    @JoinColumn(name = "userid", insertable = false, updatable = false)
    @JsonBackReference
    private User user;
    private Long userid;
}

