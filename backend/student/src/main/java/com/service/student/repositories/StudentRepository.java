package com.service.student.repositories;

import org.springframework.stereotype.Repository;

import com.service.student.entities.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer>{
	@Query(value = "SELECT * FROM students s WHERE s.email_id = ?1 AND s.password = ?2 ", nativeQuery = true)
	 Student findByEmailIdAndPassword(String emailId, String password);
}
