package com.hungry.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Tuple;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hungry.entities.AccessToken;
import com.hungry.entities.HungryUser;
import com.hungry.models.Status;

import ch.qos.logback.classic.Logger;

@Repository
public class HungryUserRepository {

	private static final Logger log = (Logger) LoggerFactory.getLogger(HungryUserRepository.class);
	@PersistenceContext
	public EntityManager entityManager;

	public boolean isSaved(HungryUser user) {
		entityManager.persist(user);
		return true;
	}

	public Status findHungryUserByPhone(String phone) {

		try {

			String SQL = "SELECT ";
			SQL += "user_password,access_token,expires,access_date";
			SQL += " FROM ";
			SQL += "hungry_users ";
			SQL += " WHERE phone_number='";
			SQL += phone;
			SQL += "'";
			 List<Tuple> results = entityManager.createNativeQuery(SQL, Tuple.class).getResultList(); 
			 
			 Tuple row = results.get(0);
			 
			 //return new AccessToken();
			 
			 System.out.println(row.get("user_password"));
			 

		} catch (Exception e) {
			log.error("findHungryUserByPhone : " + e.getMessage());
			return new Status(HungryUserRepository.class, "findHungryUserByPhone", e.getLocalizedMessage());
			
		}

		return new Status(HungryUserRepository.class, "findHungryUserByPhone","");
	}

}
