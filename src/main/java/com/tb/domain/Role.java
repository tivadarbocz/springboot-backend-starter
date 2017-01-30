package com.tb.domain;

import javax.persistence.*;

/**
 * Created by Tivadar Bocz on 2017.01.30..
 */
@Entity
public class Role {
    private Long id;
    private Long userId;
    private String role;

    public Role(Long userId, String role) {
        this.userId = userId;
        this.role = role;
    }

    public Role() {
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
    @Column(name = "user_id", nullable = false, insertable = true, updatable = true)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "role", nullable = true, insertable = true, updatable = true, length = 255)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}