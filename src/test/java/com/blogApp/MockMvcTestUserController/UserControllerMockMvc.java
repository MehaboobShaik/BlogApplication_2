package com.blogApp.MockMvcTestUserController;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.blogApp.controllers.UserContoller;
import com.blogApp.modelDto.UserDto;
import com.blogApp.serviceImpl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;


@AutoConfigureMockMvc
@SpringBootTest()
public class UserControllerMockMvc {

	@Autowired
    private MockMvc mockMvc;
	
	@InjectMocks
	private UserContoller userContoller;
	
	@Mock
	private UserServiceImpl userServiceImpl;
	
	@BeforeEach
	public void setUp() {
		userContoller = new UserContoller(userServiceImpl);
        mockMvc = MockMvcBuilders.standaloneSetup(userContoller).build();
		
	}
	@Test
	public void test_getAllCountries() throws Exception {
		List<UserDto> arrayList = new ArrayList<UserDto>();
		arrayList.add(new UserDto(1015, "Test", "Test@gmail.com", "TET", "GOOD TESTER certified by alians"));
		arrayList.add(new UserDto(1016, "Test", "Test@gmail.com", "TET", "GOOD TESTER certified by alians"));
		when(userServiceImpl.getAllUsers()).thenReturn(arrayList);
	    int status = this.mockMvc.perform(get("/user/getallusers"))
		            .andExpect(status().isFound()).andReturn().getResponse().getStatus();
	     assertThat(status).isEqualTo(302);
		          
	}
	
	@Test
	public void createUser() throws Exception {
		UserDto userDto = new UserDto(1015, "Test", "Test@gmail.com", "TET", "GOOD TESTER certified by alians");
		when(userServiceImpl.createUser(userDto)).thenReturn(userDto);
		ObjectMapper mapper = new ObjectMapper();
		String jsonbody = mapper.writeValueAsString(userDto);
		MvcResult status = this.mockMvc.perform(post("/user/createuser")
				     .content(jsonbody)
				     .contentType(MediaType.APPLICATION_JSON))
	                 .andExpect(status().isCreated()).andReturn();
	    int status2 = status.getResponse().getStatus();  
	    assertThat(status2).isEqualTo(201);
	}
	
//	@Test
//	public void updateUser() throws Exception {
//		UserDto updatedUserDto = new UserDto(1015, "TestUpdated", "UpdateTest@gmail.com", "TET", "GOOD TESTER certified by alians");
//		when(userServiceImpl.UpdateUser(updatedUserDto,1015)).thenReturn(updatedUserDto);
//		ObjectMapper mapper = new ObjectMapper();
//		String jsonbody2 = mapper.writeValueAsString(updatedUserDto);
//		System.out.println("jsonbody2::->"+jsonbody2);
//		this.mockMvc.perform(put("/user/updateuser/{userId}",1015))
//		          .contentType(MediaType.APPLICATION_JSON)
//	             .andExpect(status().isCreated())
//	             .andExpect(MockMvcResultMatchers.jsonPath(".name").value("TestUpdated"));
//		System.out.println(jsonbody2);
// 
//	}
}
