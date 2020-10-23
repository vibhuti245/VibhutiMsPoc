package com.ibm.ms.account.repository;

import org.springframework.data.repository.CrudRepository;

import com.ibm.ms.account.model.DAOUser;

public interface UserDao extends CrudRepository<DAOUser, Integer> {
	DAOUser findByUsername(String username);
}