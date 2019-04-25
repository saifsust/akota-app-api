package com.hungry.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hungry.dbs.DbManager;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.core.net.SyslogOutputStream;

@Repository("dbManagerRepository")
@Transactional
public class DbManagerRepository {

	private static final Logger log = (Logger) LoggerFactory.getLogger(UserRepository.class);

	@PersistenceContext
	private EntityManager em;
	
	public void execution() {
      
		try {
			DbManager managers[] = DbManager.values();
			for(DbManager manager:managers) {
				em.createNativeQuery(manager.getQuery()).executeUpdate();
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage());
		}
		
	}
	
	
}
