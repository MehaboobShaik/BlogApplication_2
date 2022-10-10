package com.blogApp.controllers;

import java.util.List;

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
import com.blogApp.modelDto.CategoryDto;
import com.blogApp.services.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryContoller {
       
	@Autowired
	private CategoryService  categoryService;
	
	@PostMapping("/createCategory")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
		CategoryDto createCategory = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createCategory ,HttpStatus.CREATED);
	}
	
	@PutMapping("/updateCategory/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable Integer categoryId){
		CategoryDto updateCategory = this.categoryService.UpdateCategory(categoryDto,categoryId);
		return new ResponseEntity<CategoryDto>(updateCategory ,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deleteCategory/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId){
		this.categoryService.DeleteCategory(categoryId);
		ApiResponse apiResponse = new ApiResponse("user deleted sucessfully",false);
		return new ResponseEntity<ApiResponse>(apiResponse ,HttpStatus.OK);
	}
	
	@GetMapping("/getCategory/{categoryId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId){
		CategoryDto category = this.categoryService.getCategory(categoryId);
		return new ResponseEntity<CategoryDto>(category ,HttpStatus.FOUND);
	}
	@GetMapping("/getAllCategories")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		List<CategoryDto> allCategories = this.categoryService.getAllCategories();
		return new ResponseEntity<List<CategoryDto>>(allCategories ,HttpStatus.FOUND);
	}
	
	
	
	 
}
