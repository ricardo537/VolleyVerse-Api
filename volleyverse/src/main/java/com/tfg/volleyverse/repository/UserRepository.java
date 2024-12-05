package com.tfg.volleyverse.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfg.volleyverse.model.User;

@Repository
public interface UserRepository extends JpaRepository <User, UUID> {

	User findByEmailAndPassword(String email, String password);
	
	User save(User user);
	
	void delete(User user);
	
}
