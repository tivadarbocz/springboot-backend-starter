package com.tb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Tivadar BOcz on 2016.10.17..
 */
@Entity
@Table(name = "user"/*, schema = "public"*/)
public class User {
    private Long id;
    private String username;
    private String password;
    /* private String email;
     private String firstName;
     private String lastName;*/
    //private String role;
    private Boolean disabled;
    /*private Timestamp createdOn;
    private String createdBy;*/

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, Boolean disabled) {
        this.username = username;
        this.password = password;
        this.disabled = disabled;
    }

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username", nullable = true, insertable = true, updatable = true, length = 64)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @JsonIgnore
    @Column(name = "password", nullable = true, insertable = true, updatable = true, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /* @Basic
     @Column(name = "email", nullable = true, insertable = true, updatable = true, length = 128)
     public String getEmail() {
         return email;
     }

     public void setEmail(String email) {
         this.email = email;
     }

     @Basic
     @Column(name = "first_name", nullable = true, insertable = true, updatable = true, length = 64)
     public String getFirstName() {
         return firstName;
     }

     public void setFirstName(String firstName) {
         this.firstName = firstName;
     }

     @Basic
     @Column(name = "last_name", nullable = true, insertable = true, updatable = true, length = 64)
     public String getLastName() {
         return lastName;
     }

     public void setLastName(String lastName) {
         this.lastName = lastName;
     }

     @Basic
     @Column(name = "role", nullable = true, insertable = true, updatable = true, length = 64)
     public String getRole() {
         return role;
     }

     public void setRole(String role) {
         this.role = role;
     }
 */
    public Boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }
/*
    @Basic
    @Column(name = "created_on", nullable = true, insertable = true, updatable = true)
    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    @Basic
    @Column(name = "created_by", nullable = true, insertable = true, updatable = true, length = 64)
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
*/

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", disabled=" + disabled +
                '}';
    }
}