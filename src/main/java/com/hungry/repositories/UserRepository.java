package com.hungry.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hungry.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	/**
	 * @param phone receive a phone number and search hungry_users table to get user
	 *              info.
	 * @return user if find correspondanding user
	 */

	@Query(value = "SELECT * FROM hungry_users WHERE phone_number = :phone_number", nativeQuery = true)
	public User findUserByPhoneNumber(@Param("phone_number") String phoneNumber);

	/**
	 * @return id for last insert values in hungry_users table
	 */
	@Query(value = "SELECT LAST_INSERT_ID()", nativeQuery = true)
	public int lastInsertedId();

	/**
	 * @return maximum id from hungry_users table
	 */
	///SELECT  ifnull((select MAX(user_id) as user_id FROM hungry_users),0)
	
	//@Query(value = "SELECT MAX(user_id) as user_id FROM hungry_users", nativeQuery = true)
	@Query(value = "SELECT  IFNULL((SELECT MAX(user_id) as user_id FROM hungry_users),0)", nativeQuery = true)
	public int findMaxUserId();

	/**
	 * update or insert user profile picture 
	 * @param imgUrl is the image link
	 * @param userId is the user id
	 */
	@Transactional
	@Modifying
	@Query(value = "UPDATE hungry_users SET hungry_users.user_img = :img WHERE hungry_users.user_id= :id", nativeQuery = true)
	public void updateUserImage(@Param("img") String imgUrl, @Param("id") int userId);

}
