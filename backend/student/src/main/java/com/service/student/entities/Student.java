package com.service.student.entities;

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
@Table(name = "students")
public class Student {

    @Id
    @Column(name = "student_id",unique = true,nullable = false)
    private int studentID;

    @Column(name = "student_full_name",nullable = false)
    private String studentFullName;

    @Column(name = "class",nullable = false)
    private String className;

    @Column(name = "division",nullable = false)
    private String division;

    @Column(name = "roll_no",nullable = false)
    private String rollNo;

    @Column(name = "email_id", nullable = false, unique = true) 
    private String emailId;

    @Column(name = "password", nullable = false) 
    private String password;

}
