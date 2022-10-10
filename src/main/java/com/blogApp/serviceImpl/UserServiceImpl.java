package com.blogApp.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogApp.Repository.UserRepository;
import com.blogApp.customexceptions.ResourceNotFoundException;
import com.blogApp.modelDto.UserDto;
import com.blogApp.models.User;
import com.blogApp.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		User InsertedUser = this.userRepository.save(user);
		UserDto userDto2 = this.modelMapper.map(InsertedUser, UserDto.class);
		return userDto2;
	}

	@Override
	public UserDto UpdateUser(UserDto userDto, Integer userId) {
	  
	   User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","id", userId));
	   
	   user.setName(userDto.getName());
	   user.setEmail(userDto.getEmail());
	   user.setPassword(user.getPassword());
	   user.setAbout(userDto.getAbout());
	   User updatedUser = this.userRepository.save(user);
	   UserDto userDto2 = this.modelMapper.map(updatedUser, UserDto.class);
	   return userDto2;
		
	}

	@Override
	public void DeleteUSer(Integer userId) {
		
		User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","id", userId));
        this.userRepository.delete(user);
	}

	@Override
	public UserDto getUSer(Integer userId) {
		User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","id", userId));
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
    }

	@Override
	public List<UserDto> getAllUSers() {
         List<User> users = this.userRepository.findAll();
         List<UserDto> collect = users.stream().map(user ->modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
		return collect;
	}

	
}
