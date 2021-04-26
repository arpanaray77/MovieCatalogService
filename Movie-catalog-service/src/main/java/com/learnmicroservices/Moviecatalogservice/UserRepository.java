package com.learnmicroservices.Moviecatalogservice;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.learnmicroservices.Moviecatalogservice.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
  
	Optional<User> findByUsername(String username);
}