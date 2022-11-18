package com.blogApp.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogApp.customexceptions.ApiResponse;
import com.blogApp.modelDto.UserDto;
import com.blogApp.services.UserService;

@RestController
@RequestMapping("/user")
public class UserContoller {
          
	@Autowired
	private UserService userService;
	
	public UserContoller(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping("/createuser")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		UserDto createUser = this.userService.createUser(userDto);
		return new ResponseEntity<UserDto>(createUser,HttpStatus.CREATED);
	}
	
	@PutMapping("/updateuser/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto ,@PathVariable Integer userId){
		UserDto updateUser = this.userService.UpdateUser(userDto,userId);
		System.out.println("updateUser:::::->"+updateUser);
		return new ResponseEntity<UserDto>(updateUser,HttpStatus.CREATED);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/deleteuser/{userId}")
	public ResponseEntity<ApiResponse> DeleteUser(@PathVariable Integer userId){
		String deleteUSer = this.userService.DeleteUSer(userId);
		ApiResponse apiResponse = new ApiResponse("user deleted sucessfully", true);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.FOUND);
	}
	@GetMapping("/getuser/{userId}")
	public ResponseEntity<UserDto> getUser(@PathVariable Integer userId){
		UserDto userDto = this.userService.getUSer(userId);
		userDto.setPassword("Hidden");
		return new ResponseEntity<UserDto>(userDto,HttpStatus.FOUND);
	}
	@GetMapping("/getallusers")
	public ResponseEntity<List<UserDto>> getAllUser(){
		 List<UserDto> allUSers = this.userService.getAllUsers();
		return new ResponseEntity<List<UserDto>>(allUSers,HttpStatus.FOUND);
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
		int roleId =502;
		UserDto registerUser = this.userService.registerUser(userDto,roleId);
		return new ResponseEntity<UserDto>(registerUser,HttpStatus.CREATED);
	}
}
