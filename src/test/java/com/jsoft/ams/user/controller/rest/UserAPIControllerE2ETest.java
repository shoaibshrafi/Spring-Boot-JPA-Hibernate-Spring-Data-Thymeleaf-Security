package com.jsoft.ams.user.controller.rest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ActiveProfiles("unit-test")
@RunWith(SpringRunner.class)
@SpringBootTest (webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class UserAPIControllerE2ETest {

	@Autowired MockMvc mockMvc;

	private static final String BASE_URL = "/api/users";
	
	@Test
	@WithUserDetails("admin")
	public void test_addNewUser() throws Exception {		

		String uk = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);

		mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL)
    			.param("username", String.format("testusername_%s", uk))
    			.param("firstName", String.format("testfirstname_%s", uk))
    			.param("lastName", String.format("testlastname_%s", uk))
    			.param("email", String.format("email_%s@email.com", uk))
    			.param("password", String.format("password_%s", uk))
		)
	 	.andExpect(status().isOk());

	}

	@Test
	@WithUserDetails("admin")
	public void test_addUserRole() throws Exception {		

		String uk = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
		String username = String.format("testusername_%s", uk);
		
		mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL).header("X-TENANT-ID", "tenant1_db")
    			.param("username", username)
    			.param("firstName", String.format("testfirstname_%s", uk))
    			.param("lastName", String.format("testlastname_%s", uk))
    			.param("email", String.format("email_%s@email.com", uk))
    			.param("password", String.format("password_%s", uk))
		)
	 	.andExpect(status().isOk());

		mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/{username}/roles", username).header("X-TENANT-ID", "tenant1_db")
    			.param("roleCodes", String.format("testfirstname_%s", uk))
    			.param("lastName", String.format("testlastname_%s", uk))
    			.param("email", String.format("email_%s@email.com", uk))
    			.param("password", String.format("password_%s", uk))
		)
	 	.andExpect(status().isOk());
		
	
	}

}
