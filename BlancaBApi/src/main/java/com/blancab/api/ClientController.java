package com.blancab.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blancab.api.model.Client;
import com.blancab.api.repository.ClientRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ClientController {

  @Autowired
  ClientRepository clientRepository;

  @GetMapping("/clients")
  public ResponseEntity<List<Client>> getAllClients(@RequestParam(required = false) String name) {
    try {
      List<Client> clients = new ArrayList<Client>();

      if (name == null)
        clientRepository.findAll().forEach(clients::add);
      else
        clientRepository.findByNameContaining(name).forEach(clients::add);

      if (clients.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(clients, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/clients/{id}")
  public ResponseEntity<Client> getClientById(@PathVariable("id") long id) {
    Client client = clientRepository.findById(id);

    if (client != null) {
      return new ResponseEntity<>(client, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/clients")
  public ResponseEntity<String> createClient(@RequestBody Client client) {
    try {
      clientRepository.save(new Client(client.getName(), client.getAddress(), client.getPhone(), client.getEmail()));
      return new ResponseEntity<>("Client was created successfully.", HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/clients/{id}")
  public ResponseEntity<String> updateClient(@PathVariable("id") long id, @RequestBody Client client) {
    Client _client = clientRepository.findById(id);

    if (_client != null) {
      _client.setId(id);
      _client.setName(client.getName());
      _client.setAddress(client.getAddress());
      _client.setPhone(client.getPhone());
      _client.setEmail(client.getEmail());

      clientRepository.update(_client);
      return new ResponseEntity<>("Client was updated successfully.", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Cannot find Client with id=" + id, HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/clients/{id}")
  public ResponseEntity<String> deleteClient(@PathVariable("id") long id) {
    try {
      int result = clientRepository.deleteById(id);
      if (result == 0) {
        return new ResponseEntity<>("Cannot find Client with id=" + id, HttpStatus.OK);
      }
      return new ResponseEntity<>("Client was deleted successfully.", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Cannot delete client.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/clients")
  public ResponseEntity<String> deleteAllClients() {
    try {
      int numRows = clientRepository.deleteAll();
      return new ResponseEntity<>("Deleted " + numRows + " Client(s) successfully.", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Cannot delete clients.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @GetMapping("/clients/name")
  public ResponseEntity<List<Client>> findByName(@PathVariable("name") String name) {
    try {
      List<Client> clients = clientRepository.findByName(name);

      if (clients.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(clients, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
