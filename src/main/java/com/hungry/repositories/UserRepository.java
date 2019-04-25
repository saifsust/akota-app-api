package com.hungry.repositories;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.transaction.Transactional;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hungry.entities.AccessToken;
import com.hungry.entities.User;
import com.hungry.models.Status;

import ch.qos.logback.classic.Logger;

@Repository
public class UserRepository {

	private static final Logger log = (Logger) LoggerFactory.getLogger(UserRepository.class);
	@PersistenceContext
	public EntityManager entityManager;

	@Transactional
	public void persist(User user) throws Exception {
		entityManager.persist(user);
	}

	public AccessToken findHungryUserByPhone(String phone) throws Exception {
		AccessToken token = null;
		String SQL = "SELECT ";
		SQL += "user_id,user_password,access_token,expires,access_date";
		SQL += " FROM ";
		SQL += "hungry_users ";
		SQL += " WHERE phone_number='";
		SQL += phone;
		SQL += "'";
		Tuple result = (Tuple) entityManager.createNativeQuery(SQL, Tuple.class).getSingleResult();

		if (result != null) {
			String accessToken = (String) result.get("access_token");
			String expires = (String) result.get("expires");
			Timestamp accessDate = (Timestamp) result.get("access_date");
			int userId = (int) result.get("user_id");
			
			System.out.println(userId);
			int expire = 0;
			if (expires != null)
				expire = Integer.parseInt(expires);
			token = new AccessToken(userId, accessToken, expire, accessDate);
		}

		return token;
	}

}
