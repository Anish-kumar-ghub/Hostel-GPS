package com.service.admin.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "admins")
public class Admin {

    @Id
    @Column(name = "email_id", nullable = false, unique = true) 
    private String emailId;

    @Column(name = "admin_full_name",nullable = false)
    private String adminFullName;

    @Column(name = "password", nullable = false) 
    private String password;
    
}