package com.SpringAssignment.ClientApp;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
//import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;


import com.SpringAssignment.ClientApp.Enity.Address;
import com.SpringAssignment.ClientApp.Enity.Client;
import com.SpringAssignment.ClientApp.Exception.ClientNotfoundException;
import com.SpringAssignment.ClientApp.Exception.IdIsNotSouthAfricanIdException;
import com.SpringAssignment.ClientApp.Exception.MobileNumberNotValidException;
import com.SpringAssignment.ClientApp.Service.ClientService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ClientAppApplication.class)
@AutoConfigureMockMvc
public class ClientControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Spy
	private ClientService clientService;

	@Test
	public void should_CreateClient_and_ReturnCreatedStatus() throws Exception {
		Client input = createMock();
		input.setIDNumber("4001014800084");
		input.setMobileNumber("7865653544");
		Mockito.when(clientService.create(input)).thenReturn(input);
		mockMvc.perform(post("/clients/AddClient").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(mapToJson(input))).andExpect(status().isCreated())
				.andExpect(jsonPath("$.firstName", Matchers.equalTo("Diallo")))
				.andExpect(jsonPath("$.idNumber", Matchers.equalTo("4001014800084")))
				.andExpect(jsonPath("$.mobileNumber", Matchers.equalTo("7865653544")));

	}

	@Test
	public void shouldnot_CreateInvalidClient_and_ReturnBadRequestStatus() throws Exception {
		Client input = createMock();
		input.setFirstName(null);
		mockMvc.perform(post("/clients/AddClient").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(mapToJson(input))).andExpect(status().isBadRequest());

	}

	@Test
	public void shouldnot_CreateClient_with_duplicate_MobileNumber_and_ReturnConflictStatus() throws Exception {
		Client input = createMock();
		input.setMobileNumber("8754565356");
		mockMvc.perform(post("/clients/AddClient").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(mapToJson(input))).andExpect(status().isConflict())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof MobileNumberNotValidException))
				.andExpect(result -> assertEquals("Client already exist with Mobile Number: " + input.getMobileNumber(),
						result.getResolvedException().getMessage()));

	}

	@Test
	public void shouldnot_CreateClient_with_Invalid_SA_Id_Number_and_ReturnBadRequest_status() throws Exception {
		Client input = createMock();
		input.setIDNumber("3803014800086");
		mockMvc.perform(post("/clients/AddClient").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(mapToJson(input)))
				.andExpect(status().isBadRequest())
				.andExpect(
						result -> assertTrue(result.getResolvedException() instanceof IdIsNotSouthAfricanIdException))
				.andExpect(result -> assertEquals("SA ID number is not valid: " + input.getIDNumber(),
						result.getResolvedException().getMessage()));

	}

	@Test
	public void should_get_Client_with_Ok_Status() throws Exception {
		mockMvc.perform(get("/clients/search/?mobileNumber=8754565356").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].firstName", Matchers.equalTo("JP")))
				.andExpect(jsonPath("$[0].idNumber", Matchers.equalTo("9202204720082")))
				.andExpect(jsonPath("$[0].mobileNumber", Matchers.equalTo("8754565356")));
	}

	

	@Test
	public void shouldnot_Update_Client_and_Return_not_found_Status() throws Exception {
		Client input = createMock();
		input.setIDNumber("3801014800084");
		input.setFirstName("steve");
		mockMvc.perform(put("/clients/AddClient" + input.getIDNumber()).contentType(MediaType.APPLICATION_JSON)
				.content(mapToJson(input))).andExpect(status().isNotFound())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof ClientNotfoundException))
				.andExpect(result -> assertEquals("Client does not exist for given id Number: " + input.getIDNumber(),
						result.getResolvedException().getMessage()));
	}

	@Test
	public void should_Update_valid_Client_and_ReturnNoContentStatus() throws Exception {
		Client input = createMock();
		input.setFirstName("steve");
		mockMvc.perform(put("/clients/AddClient" + input.getIDNumber()).contentType(MediaType.APPLICATION_JSON)
				.content(mapToJson(input))).andExpect(status().isNoContent())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.firstName", Matchers.equalTo("Adam")))
				.andExpect(jsonPath("$.idNumber", Matchers.equalTo("7801014800084")))
				.andExpect(jsonPath("$.mobileNumber", Matchers.equalTo("8754565356")));
	}

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

	private Client createMock() {

		Client input = new Client("Bandile", "Dumini", "7812347986", "9112104722082",
				new Address("Johannesburg", "South Africa", "Lane3", " Lane4"));

		return input;
	}
}
