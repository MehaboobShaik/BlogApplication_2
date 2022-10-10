package com.blogApp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blogApp.modelDto.CategoryDto;
import com.blogApp.modelDto.UserDto;

@Service
public interface CategoryService {

	public CategoryDto createCategory(CategoryDto categoryDto);
	public CategoryDto UpdateCategory(CategoryDto categoryDto,Integer categoryId);
	public void DeleteCategory(Integer userId);
	public CategoryDto getCategory(Integer userId);
	public List<CategoryDto> getAllCategories();
}
