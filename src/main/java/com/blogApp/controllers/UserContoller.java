package com.blogApp.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@PostMapping("/createuser")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		UserDto createUser = this.userService.createUser(userDto);
		return new ResponseEntity<UserDto>(createUser,HttpStatus.CREATED);
	}
	
	@PutMapping("/updateuser/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto ,@PathVariable Integer userId){
		UserDto updateUser = this.userService.UpdateUser(userDto,userId);
		return new ResponseEntity<UserDto>(updateUser,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deleteuser/{userId}")
	public ResponseEntity<ApiResponse> DeleteUser(@PathVariable Integer userId){
		this.userService.DeleteUSer(userId);
		ApiResponse apiResponse = new ApiResponse("user deleted sucessfully", true);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.CREATED);
	}
	@GetMapping("/getuser/{userId}")
	public ResponseEntity<UserDto> getuser(@PathVariable Integer userId){
		UserDto userDto = this.userService.getUSer(userId);
		return new ResponseEntity<UserDto>(userDto,HttpStatus.FOUND);
	}
	@GetMapping("/getallusers")
	public ResponseEntity<List<UserDto>> getAlluser(){
		 List<UserDto> allUSers = this.userService.getAllUSers();
		return new ResponseEntity<List<UserDto>>(allUSers,HttpStatus.FOUND);
	}
}
