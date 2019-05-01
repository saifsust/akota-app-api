package com.hungry.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hungry.entities.User;

@Repository
public interface Debugger extends JpaRepository<User, Integer> {

	// @Query("SELECT * FROM hungry_users")
	public List<User> findAll();

	@Query(value = "SELECT * FROM hungry_users WHERE phone_number = :phone", nativeQuery = true)
	public User findUserByPhoneNumber(@Param("phone") String phone);
	
	 

	@Query(value = "SELECT LAST_INSERT_ID()", nativeQuery = true)
	int getLastInsertId();

}
