package com.service.admin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.service.admin.entities.Admin;



@Repository
public interface AdminRepository extends JpaRepository<Admin,String>{
	@Query(value = "SELECT * FROM admins ad WHERE ad.email_id = ?1 AND ad.password = ?2 ",nativeQuery = true)
	Admin findByEmailIdAndPassword(String emailId, String password);

}
