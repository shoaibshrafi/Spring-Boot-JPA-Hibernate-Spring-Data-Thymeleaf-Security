package com.jsoft.ams.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.jsoft.ams.model.User;

public interface UserRepository extends Repository<User, String>{

	@Query("SELECT u FROM User u LEFT JOIN FETCH u.userPrivileges ur where u.username = :username")
	public Optional<User> findByUsername(@Param("username") String username);
	
}
