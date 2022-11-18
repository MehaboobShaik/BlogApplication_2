package com.blogApp.controlerTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.hamcrest.core.IsNot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.blogApp.controllers.UserContoller;
import com.blogApp.customexceptions.ApiResponse;
import com.blogApp.modelDto.UserDto;
import com.blogApp.models.User;
import com.blogApp.serviceImpl.UserServiceImpl;

import junit.framework.Assert;

@SpringBootTest
public class UserControllerTest {

	@InjectMocks
	private UserContoller userContoller;
	
	@Mock
	private UserServiceImpl userServiceImpl;

	
	@BeforeEach
	public void setUp() {
 	    userContoller = new UserContoller(userServiceImpl);
	}
	@Test
	public void getAllUsers() {
		List<UserDto> arrayList = new ArrayList<UserDto>();
		arrayList.add(new UserDto(1015, "Test", "Test@gmail.com", "TET", "GOOD TESTER certified by alians"));
		arrayList.add(new UserDto(1016, "Test", "Test@gmail.com", "TET", "GOOD TESTER certified by alians"));
		when(userServiceImpl.getAllUsers()).thenReturn(arrayList);
		ResponseEntity<List<UserDto>> allUser = userContoller.getAllUser();
		assertThat(allUser.getBody().get(0).getId()).isEqualTo(arrayList.get(0).getId());
		assertEquals(HttpStatus.FOUND,allUser.getStatusCode());
	    assertThat(allUser.getBody().size()).isEqualTo(arrayList.size());
	}
	@Test
	public void getUserById() {
	    UserDto userDto = new UserDto(1017, "Test", "Test@gmail.com", "TET", "GOOD TESTER certified by alians");
		when(userServiceImpl.getUSer(1017)).thenReturn(userDto);
		ResponseEntity<UserDto> user = userContoller.getUser(userDto.getId());
		assertThat(user.getBody().getId()).isEqualTo(userDto.getId());
		assertEquals(HttpStatus.FOUND,user.getStatusCode());
	}
	@Test
	public void deleteUserById() {
	    UserDto userDto = new UserDto(1017, "Test", "Test@gmail.com", "TET", "GOOD TESTER certified by alians");
		when(userServiceImpl.getUSer(1017)).thenReturn(userDto);
		ResponseEntity<ApiResponse> deleteUser = userContoller.DeleteUser(userDto.getId());
		assertThat(deleteUser.getBody().getMessege()).isEqualTo("user deleted sucessfully");
	}
	@Test
	public void createUser() {
	    UserDto userDto = new UserDto(1017, "Test", "Test@gmail.com", "TET", "GOOD TESTER certified by alians");
		when(userServiceImpl.createUser(userDto)).thenReturn(userDto);
		ResponseEntity<UserDto> createUser = userContoller.createUser(userDto);
		assertThat(createUser.getBody().getId()).isEqualTo(userDto.getId());
	}
	
}

