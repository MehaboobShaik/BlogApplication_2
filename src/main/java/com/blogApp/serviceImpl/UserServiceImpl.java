package com.blogApp.serviceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blogApp.Repository.RoleRespository;
import com.blogApp.Repository.UserRepository;
import com.blogApp.customexceptions.ResourceNotFoundException;
import com.blogApp.modelDto.RoleDto;
import com.blogApp.modelDto.UserDto;
import com.blogApp.models.Role;
import com.blogApp.models.User;
import com.blogApp.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RoleRespository roleRespository;

	@Autowired
	private PasswordEncoder encoder;

	public UserServiceImpl() {

	}
	public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RoleRespository roleRespository,
			PasswordEncoder encoder) {
		super();
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
		this.roleRespository = roleRespository;
		this.encoder = encoder;
	}


    @Override
	public UserDto createUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		user.setPassword(this.encoder.encode(userDto.getPassword()));
	    User InsertedUser = this.userRepository.save(user);
		UserDto userDto2 = this.modelMapper.map(InsertedUser, UserDto.class);
		return userDto2;
	}

	@Override
	public UserDto UpdateUser(UserDto userDto, Integer userId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
		if (userDto.getName() != null) {
			user.setName(userDto.getName());
		}
		if (userDto.getEmail() != null) {
			user.setEmail(userDto.getEmail());
		}
		if (userDto.getPassword() != null) {
			user.setPassword(this.encoder.encode(userDto.getPassword()));
		}
		if (userDto.getAbout() != null) {
			user.setAbout(userDto.getAbout());
		}
		User updatedUser = this.userRepository.save(user);
		UserDto userDto2 = this.modelMapper.map(updatedUser, UserDto.class);
		return userDto2;

	}

	@Override
	public String DeleteUSer(Integer userId) {

		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
		if(user!=null) {
		this.userRepository.delete(user);
		return "User deleted sucessfully";
		}else {
			return "Something went wrong";
		}
		
	}

	@Override
	public UserDto getUSer(Integer userId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
	}

	@Override
	public List<UserDto> getAllUsers() {

		List<User> users = this.userRepository.findAll();
		List<UserDto> collect = users.stream().map(user -> modelMapper.map(user, UserDto.class))
				.collect(Collectors.toList());
		return collect;

	}

	@Override
	public UserDto registerUser(UserDto userDto,Integer RoleId) {

		Role role = roleRespository.findById(RoleId).get();
		User user = this.modelMapper.map(userDto, User.class);
		user.setPassword(this.encoder.encode(userDto.getPassword()));
		user.getRoleSet().add(role);
		User user2 = this.userRepository.save(user);
		UserDto userDto2 = this.modelMapper.map(user2, UserDto.class);
		return userDto2;
	}

}
