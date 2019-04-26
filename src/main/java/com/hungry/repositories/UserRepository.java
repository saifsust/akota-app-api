package com.hungry.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.transaction.Transactional;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hungry.dbs.DbManager;
import com.hungry.entities.User;

import ch.qos.logback.classic.Logger;

@Repository
public class UserRepository {

	private String SQL;
	private static final Logger LOG = (Logger) LoggerFactory.getLogger(UserRepository.class);
	@PersistenceContext
	public EntityManager entityManager;

	@Transactional
	public void persist(User user) {

		try {
			entityManager.persist(user);
		} catch (Exception ex) {
			LOG.error("persist : " + ex.getMessage());
			LOG.error("persist : " + ex.getCause().toString());
			LOG.error("persist : " + ex.getLocalizedMessage());
		}

	}

	public int findMaxUserId() {

		try {
			SQL = "select max(user_id) as user_id  from  " + DbManager.USERS.value();
			Tuple max = (Tuple) entityManager.createNativeQuery(SQL, Tuple.class).getSingleResult();
			return max.get("user_id") == null ? 0 : (int) max.get("user_id");

		} catch (Exception ex) {
			LOG.error("findMaxUserId : " + ex.getMessage());
			LOG.error("findMaxUserId : " + ex.getCause().toString());
			LOG.error("findMaxUserId : " + ex.getLocalizedMessage());
			return 0;
		}

	}

	public Tuple findUserByPhone(String phone) {

		try {
			SQL = "SELECT ";
			SQL += "user_password,access_token,expires,access_date";
			SQL += " FROM ";
			SQL += "hungry_users ";
			SQL += " WHERE phone_number='";
			SQL += phone;
			SQL += "'";
			Tuple result = (Tuple) entityManager.createNativeQuery(SQL, Tuple.class).getSingleResult();
			LOG.debug("findHungryUserByPhone : " + result.get("user_password"));
			return result;
		} catch (Exception ex) {
			LOG.error("findHungryUserByPhone : " + ex.getMessage());
			return null;
		}

	}

}
