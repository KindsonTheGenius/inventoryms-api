package com.kindsonthegenius.inventoryms_springboot_api.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.kindsonthegenius.inventoryms_springboot_api.security.models.Auditable;
import com.kindsonthegenius.inventoryms_springboot_api.security.models.SecureToken;
import com.kindsonthegenius.inventoryms_springboot_api.security.models.UserPrivilegeAssignment;
import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "mobile"),
        @UniqueConstraint(columnNames = "username")
})
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String firstName;

    @Column(length = 50)
    private String middleName;

    @Column(length = 50)
    private String lastName;

    @Column(length = 50)
    private String username;

    @Column(length = 15)
    private String mobile;

    @Column(length = 50)
    private String email;

    private String imageUrl;
    private String occupation;

    @OneToMany(mappedBy = "user")
    private List<SecureToken> tokens;

    private boolean accountVerified;
    private boolean loginDisabled;

    @ManyToOne
    @JoinColumn(name = "locationid", insertable = false, updatable = false)
    private Location location;
    private Integer locationid;

    private String phone;
    private Date dateOfBirth;
    private String company;

    @Column(nullable = false)
    private String password;

    private String passwordHash;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;

    @Column(columnDefinition = "TINYTEXT")
    private String intro;

    @Column(columnDefinition = "TEXT")
    private String profile;

    @OneToMany(mappedBy = "user")
    private List<UserPrivilegeAssignment> privileges;

    @ElementCollection
    @CollectionTable(name = "social_links", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "url")
    @MapKeyColumn(name = "platform")
    private Map<String, String> socialLinks;
    /*
        In this case, the socialLinks map holds platform names (e.g., "twitter", "linkedin") as keys and their URLs as values.
        The @ElementCollection annotation is used to store this map in a separate table social_links
     */
    //@JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<Task> tasks;

    //@JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    //@JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<Conversation> conversations;

    //@JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<Project> projects;

    //@JsonManagedReference
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Message> messages;

    @Override
    public String toString(){
        return "User{" +
                "id=" + id +
                ", name='" + firstName + '\'' +
                ", lastName'" + lastName + '\'' +
                // Do not include privileges here to prevent recursion
                '}';
    }

}
