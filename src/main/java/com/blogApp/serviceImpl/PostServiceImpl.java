package com.blogApp.serviceImpl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogApp.Repository.CategoryRepository;
import com.blogApp.Repository.PostRepository;
import com.blogApp.Repository.UserRepository;
import com.blogApp.customexceptions.ResourceNotFoundException;
import com.blogApp.modelDto.PostDto;
import com.blogApp.models.Category;
import com.blogApp.models.Posts;
import com.blogApp.models.User;
import com.blogApp.services.PostService;

@Service
public class PostServiceImpl implements PostService{
  
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public PostDto createPost(PostDto postDto, Integer UserId, Integer categoryId) {
		Posts posts = this.modelMapper.map(postDto, Posts.class);
		User user = this.userRepository.findById(UserId).orElseThrow(()->new ResourceNotFoundException("userId", "id", UserId));
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("categoryId", "id", categoryId));
        posts.setUser(user);
        posts.setCategory(category);
        Posts insertedPost = postRepository.save(posts);
        PostDto insertedpostDto = this.modelMapper.map(insertedPost, PostDto.class);
		return insertedpostDto;
	}

	@Override
	public PostDto UpdatePost(PostDto postDto, Integer postId) {
		Posts post = this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("userId", "id", postId));
        if(postDto.getTitle()!=null) {
		post.setTitle(postDto.getTitle());
        }
        if(postDto.getContent()!=null) {
        post.setContent(postDto.getContent());
        }
        if(postDto.getImage()!=null) {
        post.setImage(postDto.getImage());
        }
        Posts updatedPost = postRepository.save(post);
        PostDto updatedPostDto = this.modelMapper.map(updatedPost, PostDto.class);
		return updatedPostDto;
	}

	@Override
	public void DeletePost(Integer postId,Integer userId,Integer categoryId) {
		
		Posts post = this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("userId", "id", postId));
		User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","id", userId));
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category", "id", categoryId));
        user.getSet().remove(post);
        userRepository.save(user);
        category.getList().remove(post);
        categoryRepository.save(category);
		this.postRepository.delete(post);
	}

	@Override
	public PostDto getPost(Integer postId) {
		Posts post = this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("userId", "id", postId));
	    PostDto postDto = this.modelMapper.map(post, PostDto.class);
		return postDto;
	}

	@Override
	public List<PostDto> getAllPosts() {
		List<Posts> posts = this.postRepository.findAll();
		List<PostDto> collect = posts.stream().map(post->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return collect;
	}
	
	
}
