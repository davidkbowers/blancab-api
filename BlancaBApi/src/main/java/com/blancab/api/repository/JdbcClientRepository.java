package com.blancab.api.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.blancab.api.model.Client;

@Repository
public class JdbcClientRepository implements ClientRepository {

	  @Autowired
	  private JdbcTemplate jdbcTemplate;

	  @Override
	  public int save(Client client) {
	    return jdbcTemplate.update("INSERT INTO clients (name, address, phone, email) VALUES(?,?,?,?)",
	        new Object[] { client.getName(), client.getAddress(), client.getPhone(), client.getEmail() });
	  }

	  @Override
	  public int update(Client client) {
	    return jdbcTemplate.update("UPDATE clients SET name=?, address=?, phone=?, email=? WHERE id=?",
	        new Object[] { client.getName(), client.getAddress(), client.getPhone(), client.getEmail(), client.getId() });
	  }

	  @Override
	  public Client findById(Long id) {
	    try {
	      Client client = jdbcTemplate.queryForObject("SELECT * FROM clients WHERE id=?",
	          BeanPropertyRowMapper.newInstance(Client.class), id);

	      return client;
	    } catch (IncorrectResultSizeDataAccessException e) {
	      return null;
	    }
	  }
	  
	  @Override
	  public int deleteById(Long id) {
	    return jdbcTemplate.update("DELETE FROM clients WHERE id=?", id);
	  }

	  @Override
	  public List<Client> findAll() {
	    return jdbcTemplate.query("SELECT * from blancabdb.clients", BeanPropertyRowMapper.newInstance(Client.class));
	  }

	  @Override
	  public List<Client> findByName(String name) {
	    return jdbcTemplate.query("SELECT * from clients WHERE name=?",
	        BeanPropertyRowMapper.newInstance(Client.class), name);
	  }

	  @Override
	  public List<Client> findByNameContaining(String name) {
	    String q = "SELECT * from clients WHERE name ILIKE '%" + name + "%'";

	    return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Client.class));
	  }

	  @Override
	  public int deleteAll() {
	    return jdbcTemplate.update("DELETE from clients");
	  }
	}