package com.blogApp.services;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.blogApp.customexceptions.ResourceNotFoundException;
import com.blogApp.modelDto.UserDto;

@Service
public interface UserService {
            
	public UserDto createUser(UserDto userDto);
	public UserDto UpdateUser(UserDto userDto,Integer userID) throws ResourceNotFoundException;
	public void DeleteUSer(Integer userId);
	public UserDto getUSer(Integer userId);
	public List<UserDto> getAllUSers();
}
