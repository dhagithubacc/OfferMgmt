/*package com.caveofprogramming.spring.web.controller;

import com.caveofprogramming.spring.web.model.User;
import com.caveofprogramming.spring.web.repository.UserRepository;
import com.caveofprogramming.spring.web.validator.UserValidator;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.caveofprogramming.spring.web.controller.UserController;


@RunWith(SpringRunner.class)
//@WebMvcTest(UserController.class)
@SpringBootTest
public class UserControllerTest{
	
	@Test
	public void contextLoads() {
	}
	 private static final Long TEST_USER_ID = new Long(1);
	 
	
	 @Autowired
		private WebApplicationContext webApplicationContext;
	    private MockMvc mockMvc;
	
	@MockBean
	private UserRepository userRepository;
	
	@Autowired
	private UserValidator userValidator;

	private  User  unitUser;
	
	
   @Before
	    public void setup() {
	   mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	   unitUser = new User();
	   unitUser.setId(TEST_USER_ID);
	   unitUser.setUsername("unitUser");
	   unitUser.setPassword("Test0004");
	   unitUser.setPasswordConfirm("Test0004");
	   unitUser.setEmail("ddhara10@gmail.com");
	   unitUser.setEnabled(true);
	   given(this.userRepository.findOne(TEST_USER_ID)).willReturn(unitUser);
   }
	 
   @Test
	public void testRegistrationSuccess() throws Exception{
		
	   mockMvc.perform(post("/newaccount")
			    .param("userName", "Test0004")
	            .param("password", "Test0004")
	            .param("passwordConfirm", "Test0004")
	            .param("email", "ddhara10@gmail.com")
	            //.param("enabled", "true")
	        )
	   			.andExpect(status().isOk());
	   			//.andExpect(content().contentType("application/json;charset=UTF-8"));
			
		
	}
   
   @Test
  	public void testRegistrationError() throws Exception{
  		
  	   mockMvc.perform(post("/newaccount")
  	            .param("userName", "Test0005")
  	            .param("password", "Test0005")
  	            .param("passwordConfirm", "Test0005")
  	            .param("email", "ddhara10@gmail.com")
  	            .param("enabled", "true")
  	        )
		       .andExpect(status().isOk())
		       .andExpect(model().attributeHasErrors("unitUser"))
		       .andExpect(model().attributeHasFieldErrors("unitUser", "email"))
		       .andExpect(model().attributeHasFieldErrors("unitUser", "true"))
		       .andExpect(view().name("redirect:/accountcreated"));  			
  		
  	}

	
	@Test
	public void testShowAdminSuccess() throws Exception {

		 mockMvc.perform(get("/admin", TEST_USER_ID))
         .andExpect(status().isOk())
         .andExpect(model().attribute("user", hasProperty("userName", is("Test0005"))))
         .andExpect(model().attribute("user", hasProperty("password", is("Test0005"))))
         .andExpect(model().attribute("user", hasProperty("passwordConfirm", is("Test0005"))))
         .andExpect(model().attribute("user", hasProperty("email", is("email"))))
         .andExpect(model().attribute("user", hasProperty("enabled", is(true))))
         .andExpect(view().name("admin"));
	}
	
	@Test
	public void testShowAdminError() throws Exception {

		 mockMvc.perform(get("/admin", TEST_USER_ID))
         .andExpect(status().isOk())
         .andExpect(model().attribute("user", hasProperty("userName", is("Test0005"))))
         .andExpect(model().attribute("user", hasProperty("password", is("Test0005"))))
         .andExpect(model().attribute("user", hasProperty("passwordConfirm", is("Test0005"))))
         .andExpect(model().attribute("user", hasProperty("email", is("email"))))
         .andExpect(model().attribute("user", hasProperty("enabled", is(false))))
         .andExpect(view().name("admin"));
	}

}
*/