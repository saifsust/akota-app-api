package com.hungry.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hungry.repositories.DbManagerRepository;

@Service
public class DbManagerService {

	@Autowired
	public DbManagerRepository dbManagerRepository;
	
	public void execution() {
		dbManagerRepository.execution();
	}
	
}
