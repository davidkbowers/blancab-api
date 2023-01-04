package com.blancab.api.repository;

import java.util.List;

import com.blancab.api.model.Client;

public interface ClientRepository {
	  int save(Client client);

	  int update(Client client);

	  Client findById(Long id);

	  int deleteById(Long id);

	  List<Client> findAll();

	  List<Client> findByName(String name);

	  List<Client> findByNameContaining(String name);

	  int deleteAll();
	}
