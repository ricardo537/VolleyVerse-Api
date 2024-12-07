package com.tfg.volleyverse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfg.volleyverse.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	User findByEmailAndPassword(String email, String password);
	
	User findByEmail(String email);
	
	User save (User user);
	
	void delete(User user);
}
