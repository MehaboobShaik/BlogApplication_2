package com.blogApp.ServiceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder.In;

import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.CollectionAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.blogApp.Repository.RoleRespository;
import com.blogApp.Repository.UserRepository;
import com.blogApp.modelDto.RoleDto;
import com.blogApp.modelDto.UserDto;
import com.blogApp.models.Role;
import com.blogApp.models.User;
import com.blogApp.serviceImpl.UserServiceImpl;
import com.blogApp.services.UserService;

import junit.framework.Assert;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceMockitoTest {

	private ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private PasswordEncoder encoder;

	@InjectMocks
	private UserServiceImpl userService;

	@Mock
	private UserRepository userRepository;
	
	@Mock
	private RoleRespository roleRespository;

	private List<User> myUsers;

	@BeforeEach
	void setup() {
		userService = new UserServiceImpl(userRepository, modelMapper,roleRespository,encoder);
	}

	@Test
	public void test_createUser() {
        User user = new User(1015, "Test", "Test@gmail.com", "TET", "GOOD TESTER certified by alians");
        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
        UserDto createUser = userService.createUser(userDto);
        assertEquals(user.getId(), createUser.getId());
	}
	@Test
	public void test_updateUser() {
		Optional<User> user = Optional.of(new User(1017, "Test", "Test@gmail.com", "TST", "GOOD TESTER"));
		int id = user.get().getId();
		when(userRepository.findById(id)).thenReturn(user);
	    User userTwo = new User(1017, "TestUpdate", "TestUpdate@gmail.com", "TETUPDATE", "GOOD TESTER UPDATER certified by alians");
	    when(userRepository.save(Mockito.any(User.class))).thenReturn(userTwo);
        UserDto userDto = this.modelMapper.map(userTwo, UserDto.class);
	    UserDto updateUser = userService.UpdateUser(userDto,id);
        assertThat(user.get().getId()).isEqualTo(updateUser.getId());
        assertThat(updateUser.getName()).isEqualTo("TestUpdate");
	}

	@Test
	public void test_deleteUser() {

		Optional<User> user = Optional.of(new User(1014, "Test", "Test@gmail.com", "TST", "GOOD TESTER"));
		int id = user.get().getId();
		when(userRepository.findById(id)).thenReturn(user);
		String deleteUser = userService.DeleteUSer(id);
		assertEquals(deleteUser, "User deleted sucessfully");
	}
	@Test
	public void test_getUser() {

		Optional<User> user = Optional.of(new User(1012, "Test", "Test@gmail.com", "TST", "GOOD TESTER"));
		int id = user.get().getId();
		when(userRepository.findById(id)).thenReturn(user);
		UserDto returnedUser = userService.getUSer(id);
		assertEquals(returnedUser.getId(), id);

	}
	@Test
	public void test_getAllUsers() {
		myUsers = new ArrayList<User>();
		myUsers.add(new User(100, "Test", "Test@gmail.com", "TST", "GOOD TESTER"));
		myUsers.add(new User(101, "Test", "Test@gmail.com", "TST", "GOOD TESTER"));
		when(userRepository.findAll()).thenReturn(myUsers.stream().collect(Collectors.toList()));
		List<UserDto> allUsers = userService.getAllUsers();
		List<User> collect = allUsers.stream().map(user2 -> modelMapper.map(user2, User.class))
				.collect(Collectors.toList());
		assertEquals(allUsers.size(), myUsers.size());
		assertEquals(collect.get(0).getId(), myUsers.get(0).getId());

 	}
	

	@Test
	public void test_register_users() {
		Optional<Role> role = Optional.of(new Role(500,"ROLE_NORMAL"));
		HashSet<Role> RoleSet = new HashSet<Role>();
		RoleSet.add(role.get());
		int RoleId = role.get().getId();
		User user = new User(1015, "Test", "Test@gmail.com", "TET", "GOOD TESTER certified by alians",RoleSet);
		UserDto userDto = this.modelMapper.map(user,UserDto.class);
		when(roleRespository.findById(500)).thenReturn(role);
		when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
		UserDto registerUser = userService.registerUser(userDto,RoleId);
		RoleDto roleDto = this.modelMapper.map(role.get(),RoleDto.class);
		Set<RoleDto> roleSet2 = registerUser.getRoleSet();
		int id = roleSet2.iterator().next().getId();
		assertThat(registerUser.getId()).isEqualTo(user.getId());
		assertThat(registerUser.getRoleSet().iterator().next().getId()).isEqualTo(roleDto.getId());
	
 	}


	
}
