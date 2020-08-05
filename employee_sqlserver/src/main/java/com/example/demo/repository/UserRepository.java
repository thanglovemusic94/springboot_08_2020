package com.example.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	
	@Query("select u from User u where "
			+ "concat(u.name, u.email, u.phone) "
			+ "like %?1%")
	List<User> findAllUser(String keyword);

}
