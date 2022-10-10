package com.blogApp.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogApp.Repository.CategoryRepository;
import com.blogApp.customexceptions.ResourceNotFoundException;
import com.blogApp.modelDto.CategoryDto;
import com.blogApp.models.Category;
import com.blogApp.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = this.modelMapper.map(categoryDto, Category.class);
		Category InsertedCategory = categoryRepository.save(category);
		CategoryDto insertedCategoryDto = this.modelMapper.map(InsertedCategory, CategoryDto.class);
		return insertedCategoryDto;
	}

	@Override
	public CategoryDto UpdateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category", "id", categoryId));
		category.setTitle(categoryDto.getTitle());
		Category updatedCategory = this.categoryRepository.save(category);
		CategoryDto updatedCategoryDto = this.modelMapper.map(updatedCategory, CategoryDto.class);
        return updatedCategoryDto;
	}

	@Override
	public void DeleteCategory(Integer categoryId) {
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category", "id", categoryId));
        this.categoryRepository.delete(category);
		
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category", "id", categoryId));
		CategoryDto categoryDto = this.modelMapper.map(category, CategoryDto.class);
        return categoryDto;
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> categories = this.categoryRepository.findAll();
        List<CategoryDto> collect = categories.stream().map(category -> this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
		return collect;
	}

	 
}
