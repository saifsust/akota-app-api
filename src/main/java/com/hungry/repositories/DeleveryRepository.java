package com.hungry.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hungry.entities.Delevery;

@Repository
public interface DeleveryRepository extends JpaRepository<Delevery, Integer> {

	@Query(value = "SELECT * FROM hungry_deleveries WHERE delevery_id = :delevery_id", nativeQuery = true)
	public Delevery findDeleverById(@Param("delevery_id") int deleveryId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM hungry_deleveries WHERE delevery_id = :delevery_id", nativeQuery = true)
	public void delete(@Param("delevery_id") int deleveryId);

}
