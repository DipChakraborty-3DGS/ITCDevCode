package com.SpringAssignment.ClientApp.Controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.SpringAssignment.ClientApp.Enity.Client;
import com.SpringAssignment.ClientApp.Exception.ClientNotfoundException;
import com.SpringAssignment.ClientApp.Service.ClientService;


@RestController
public class ClientController {

	@Autowired
	ClientService clientservice;
	
	@GetMapping(value = "/clients/AllClient")
	  public List<Client> listClient(){
	    return clientservice.listallclient();
	  }
	
	 @PostMapping(value = "/clients/AddClient")
	  public ResponseEntity<Client> create(@Valid @RequestBody Client Client) throws ClientNotfoundException{
		  Client createdClient= clientservice.create(Client);
		  if(createdClient==null) {
			  throw new ClientNotfoundException("Client creation failed. Please provide proper deails to creat new client.");
		  }
	    return new ResponseEntity<Client>(createdClient, HttpStatus.CREATED);
	  }

	 

	  @PutMapping(value = "/clients/updateByIDNumber/{id}")
	  public ResponseEntity<Client> update(@PathVariable @NotNull String id, @Valid @RequestBody Client Client) throws ClientNotfoundException{
		  Client updatedClient= clientservice.update(id, Client);
		  if(updatedClient==null) {
			  throw new ClientNotfoundException("Clien not available for update");
		  }
	    return new ResponseEntity<Client>(updatedClient, HttpStatus.OK);
	  }

	  @DeleteMapping(value = "/clients/{id}")
	  public void delete(@PathVariable String id){
	     clientservice.delete(id);
	  }
	  
	  @GetMapping(value = "/clients/getDetails/{search}")
	  public List<Client> getClientDetails(@PathVariable String search){
	    return clientservice.findByNameOrMobileNumberOIdnumber(search);
	  }
	  
	  @GetMapping(value = "/clients/searchByName/{FirstName}")
	  public List<Client> getClientDetailsByName(@PathVariable String FirstName){
	    return clientservice.findByFirstName(FirstName);
	  }
	  
	  @GetMapping(value = "/clients/searchByMobileNumber/{MobileNumber}")
	  public List<Client> getClientDetailsByMobileNumber(@PathVariable String MobileNumber){
	    return clientservice.findByMobileNumber(MobileNumber);
	  }
	  
	  @GetMapping(value = "/clients/searchByIDNumber/{IDNumber}")
	  public List<Client> getClientDetailsByIDNumber(@PathVariable String IDNumber){
	    return clientservice.findByIDNumber(IDNumber);
	  }
	
}
