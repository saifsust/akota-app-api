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
	 * @param user_id receive a phone number and search hungry_users table to get
	 *                user info.
	 * @return user if find correspondanding user
	 */

	@Query(value = "SELECT * FROM hungry_users WHERE user_id = :user_id", nativeQuery = true)
	public User findUserByUserId(@Param("user_id") int userId);

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
	@Query(value = "SELECT  IFNULL((SELECT MAX(user_id) as user_id FROM hungry_users),0)", nativeQuery = true)
	public int findMaxUserId();

	/**
	 * update or insert user profile picture
	 * 
	 * @param imgUrl is the image link
	 * @param userId is the user id
	 */
	@Transactional
	@Modifying
	@Query(value = "UPDATE hungry_users SET hungry_users.user_img = :img ,hungry_users.user_img_location = :user_img_location  WHERE hungry_users.user_id= :id", nativeQuery = true)
	public void updateUserImage(@Param("img") String imgUrl, @Param("user_img_location") String user_img_location,
			@Param("id") int userId);

	/**
	 * return user_img_location to delete or modify the user image
	 * 
	 * @param userId user id
	 * @return user image local location
	 */
	@Query(value = "SELECT user_img_location FROM hungry_users WHERE hungry_users.user_id= :user_id", nativeQuery = true)
	public String findImageLocalAddresss(@Param("user_id") int userId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM hungry_users WHERE phone_number=:phone_number", nativeQuery = true)
	public void deleteUserByPhone(@Param("phone_number") String phoneNumber);

}
