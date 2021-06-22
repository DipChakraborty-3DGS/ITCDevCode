package com.SpringAssignment.ClientApp.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;


import com.SpringAssignment.ClientApp.Enity.Address;
import com.SpringAssignment.ClientApp.Enity.Client;
import com.SpringAssignment.ClientApp.Exception.ClientNotfoundException;
import com.SpringAssignment.ClientApp.Exception.IdIsNotSouthAfricanIdException;
import com.SpringAssignment.ClientApp.Exception.IDNumberNotValid;
import com.SpringAssignment.ClientApp.Exception.MobileNumberNotValidException;
import com.SpringAssignment.ClientApp.Validation.IDNumberValidation;

@Service
public class ClientService {

	private static Map<String,Client> clientMap = new HashMap<String,Client>();
	
	// Creating static client data
	static {
		clientMap.put("9202204720082",new Client("Diallo", "Cisse", "8754565356", "9202204720082",
				new Address("Durban", "South Africa", "Lane1", " Lane2")) );
		
		clientMap.put("9112104722082", new Client("Bandile", "Dumini", "7812347986", "9112104722082",
				new Address("Johannesburg", "South Africa", "Lane3", " Lane4")));
		clientMap.put("8409106756082", new Client("Kungawo", "Nkosi", "7812347955", "8409106756082",
				new Address("Cape Town", "South Africa", "Lane1", " Lane2")));
		
			
	}
	
	// This listallclient() method will fetch all client details
	public List<Client> listallclient(){
		return clientMap.values().stream().collect(Collectors.toList());
	}
	
	// This create() method will create new clients
	public Client create(Client client) {
		
		boolean isIDValid= IDNumberValidation.isValid(client.getIDNumber().trim());
		if(isIDValid) {
			String mobNumber= client.getMobileNumber();
			String idno= client.getIDNumber();
			   
			    
			    for(Map.Entry<String, Client> clientset:clientMap.entrySet() ) {
			    	
			    	if(mobNumber.equals(clientset.getValue().getMobileNumber())) {
			    	
			    		throw new MobileNumberNotValidException("Mobile Number already exist for other client. Please enter unique Mobile Number ");
			    		
			    	}
			    	else if(idno.equals(clientset.getValue().getIDNumber())) {
			    	
			    		throw new IDNumberNotValid("ID Number already exist for other client. Please enter unique ID Number ");
			    		
			    	}
			    	
			    }
			    
			    clientMap.put(idno, client);
			    
			    
		}
		else {
			throw new IdIsNotSouthAfricanIdException("You have entered Invalid South African ID:"+client.getIDNumber()+". Please enter a valid South Africa ID of 13 digit.");
		}
		
		return client;
	}

	// This isMobileNumberExsist() method is used to check whether Mobile Number already exist for any of the client.
	//return true=> if Mobile Number exist
	//return false=> if Mobile Number does not Exist
	public static boolean isMobileNumberExsist(Client client,Map<String, Client> clientMap2) {
		
		String mobNumber= client.getMobileNumber();
		boolean flag=false;
		 for(Map.Entry<String, Client> clientset:clientMap2.entrySet() ) {
		    	
		    	if(mobNumber.equals(clientset.getValue().getMobileNumber())) {
		    	
		    		//throw new MobieNumberNotValid("Mobile Number already exist for other client. Please enter unique Mobile Number ");
		    		flag= true;
		    		return flag;
		    	}
		 }
		
		
		return flag;
		
	}
	
	//This Update() method is used to update existing client information
public Client update(String idnumber, Client client) {
		
		boolean isIDValid= IDNumberValidation.isValid(client.getIDNumber().trim());
		Client clientToUpdate = (Client) findByIDNumber(client.getIDNumber());
		if(isIDValid) {
			
			if(isMobileNumberExsist(client,clientMap))
				
			{
				throw new MobileNumberNotValidException("Mobile Number already exist for other client. Please enter unique Mobile Number ");
			}
			else {
				clientToUpdate.setMobileNumber(client.getMobileNumber());
			}
			
			clientToUpdate.setFirstName(client.getFirstName());
			clientToUpdate.setLastName(client.getLastName());
			clientToUpdate.setAddress(client.getAddress());
			
			
			
		}
		
		clientMap.put(idnumber, clientToUpdate);
		
		return client;
	}

	// This delete() method is used to delete a client having specific IDNumber provided by user
	  public  void delete(String idnumber) {
		    
		  Client client = (Client) findByIDNumber(idnumber);
		  if(client == null) {
			  throw new ClientNotfoundException("Client does not exsist with given IDNumber.");
		  }
		  else{
			  clientMap.remove(idnumber);
		  }
		  		  
		  
		  }

	  // This method is used to fetch Client details based on given Name or IDNumber or Mobile Number
		public List<Client> findByNameOrMobileNumberOIdnumber(String search) {
			return clientMap.entrySet().stream().filter(s->s.getValue().getFirstName().equalsIgnoreCase(search)||s.getValue().getMobileNumber().equalsIgnoreCase(search)||s.getValue().getIDNumber().equalsIgnoreCase(search)).map(x->x.getValue()).collect(Collectors.toList());
			
		}
		 // This method is used to fetch Client details based on given First Name 
	public List<Client> findByFirstName(String firstName) {
		return clientMap.entrySet().stream().filter(s -> firstName.equalsIgnoreCase(s.getValue().getFirstName()))
				.map(x -> x.getValue()).collect(Collectors.toList());
	}

	 // This method is used to fetch Client details based on given valid SA IDNumber
	public Client findByIDNumber(String IDNumber) {
		return (Client) clientMap.entrySet().stream().filter(s -> IDNumber.equals(s.getValue().getIDNumber()))
				.map(Map.Entry::getValue).findFirst().orElse(null);
		
		
		
	}
	
	 // This method is used to fetch Client details based on given Mobile Number
	public List<Client> findByMobileNumber(String MobNumber) {
		return clientMap.entrySet().stream().filter(s -> MobNumber.equals(s.getValue().getMobileNumber()))
				.map(x -> x.getValue()).collect(Collectors.toList());
	}

	
	
}
