package com.blogApp.serviceImpl;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.blogApp.config.PostResponse;
import com.blogApp.Repository.CategoryRepository;
import com.blogApp.Repository.PostRepository;
import com.blogApp.Repository.UserRepository;
import com.blogApp.customexceptions.ResourceNotFoundException;
import com.blogApp.modelDto.CategoryDto;
import com.blogApp.modelDto.CommentDto;
import com.blogApp.modelDto.PostDto;
import com.blogApp.modelDto.UserDto;
import com.blogApp.models.Category;
import com.blogApp.models.Comment;
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
        User user2 = insertedPost.getUser();
        UserDto userDto = this.modelMapper.map(user2, UserDto.class);
        Category category2 = insertedPost.getCategory();
        CategoryDto categoryDto = this.modelMapper.map(category2, CategoryDto.class);
        PostDto insertedpostDto = this.modelMapper.map(insertedPost, PostDto.class);
		insertedpostDto.setUserDto(userDto);
		insertedpostDto.setCategoryDto(categoryDto);
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
		User user = post.getUser();
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		Category cat = post.getCategory();
		CategoryDto categotyDto = this.modelMapper.map(cat, CategoryDto.class);
		Set<Comment> set =post.getCommentSet();
		List<CommentDto> collect = set.stream().map(comment->modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());
		PostDto postDto = this.modelMapper.map(post, PostDto.class);
		postDto.setUserDto(userDto);
		postDto.setCategoryDto(categotyDto);
		postDto.setCommentPostSet(collect);
		return postDto;
	}

	@Override
	public PostResponse getAllPosts(int pageNo, int pageSize) {
		User user = null;UserDto userDto = null;Category cat = null;
		CategoryDto categotyDto = null;Set<Comment> set = null;
		List<CommentDto> commentDtoListcollect = null;
		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<Posts> pageOfPost = this.postRepository.findAll(paging);
	    List<Posts> posts = pageOfPost.getContent();
		for (Posts post : posts) {
			user = post.getUser();
			userDto = this.modelMapper.map(user, UserDto.class);
			cat = post.getCategory();
			categotyDto = this.modelMapper.map(cat, CategoryDto.class);
			set = post.getCommentSet();
			commentDtoListcollect = set.stream().map(comment -> modelMapper.map(comment, CommentDto.class))
					.collect(Collectors.toList());
		}
		List<PostDto> collect = posts.stream().map(post -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		for (PostDto postDto : collect) {
			postDto.setUserDto(userDto);
			postDto.setCategoryDto(categotyDto);
			postDto.setCommentPostSet(commentDtoListcollect);
		}
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(collect);
		postResponse.setPageNumber(pageOfPost.getNumber());
		postResponse.setPageSize(pageOfPost.getSize());
		postResponse.setTotalElements(pageOfPost.getTotalElements());
		postResponse.setTotalPages(pageOfPost.getTotalPages());
		postResponse.setLastPages(pageOfPost.isLast());
		return postResponse;
		
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId, int pageNo, int pageSize) {
		User user = null;UserDto userDto = null;Category cat = null;
		CategoryDto categotyDto = null;Set<Comment> set = null;
		List<CommentDto> commentDtoListcollect = null;
		User userById = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("userId", "Id", userId));
	    Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Posts> findByUser = postRepository.findByUser(userById, pageable);
		List<Posts> posts = findByUser.getContent();
		for (Posts post : posts) {
			user = post.getUser();
			userDto = this.modelMapper.map(user, UserDto.class);
			cat = post.getCategory();
			categotyDto = this.modelMapper.map(cat, CategoryDto.class);
			set = post.getCommentSet();
			commentDtoListcollect = set.stream().map(comment -> modelMapper.map(comment, CommentDto.class))
					.collect(Collectors.toList());
		}
		List<PostDto> collect = posts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		for (PostDto postDto : collect) {
			postDto.setUserDto(userDto);
			postDto.setCategoryDto(categotyDto);
			postDto.setCommentPostSet(commentDtoListcollect);
		}
		return collect;
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categotyId, int pageNo, int pageSize) {
		User user = null;UserDto userDto = null;Category cat = null;
		CategoryDto categotyDto = null;Set<Comment> set = null;
		List<CommentDto> commentDtoListcollect = null;
		Category category = categoryRepository.findById(categotyId).orElseThrow(()->new ResourceNotFoundException("categoryId", "Id", categotyId));
	    Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Posts> findByCategory = postRepository.findByCategory(category, pageable);
		List<Posts> posts = findByCategory.getContent();
		for (Posts post : posts) {
			user = post.getUser();
			userDto = this.modelMapper.map(user, UserDto.class);
			cat = post.getCategory();
			categotyDto = this.modelMapper.map(cat, CategoryDto.class);
			set = post.getCommentSet();
			commentDtoListcollect = set.stream().map(comment -> modelMapper.map(comment, CommentDto.class))
					.collect(Collectors.toList());
		}
		List<PostDto> collect = posts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		for (PostDto postDto : collect) {
			postDto.setUserDto(userDto);
			postDto.setCategoryDto(categotyDto);
			postDto.setCommentPostSet(commentDtoListcollect);
		}
		return collect;
	}

	@Override
	public List<PostDto> getPostByTitle(String title, int pageNo, int pageSize) {
		User user = null;UserDto userDto = null;Category cat = null;
		CategoryDto categotyDto = null;Set<Comment> set = null;
		List<CommentDto> commentDtoListcollect = null;
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Posts> findByTitleContaining = this.postRepository.findByTitleContaining(title, pageable);
		List<Posts> posts = findByTitleContaining.getContent();
		for (Posts post : posts) {
			user = post.getUser();
			userDto = this.modelMapper.map(user, UserDto.class);
			cat = post.getCategory();
			categotyDto = this.modelMapper.map(cat, CategoryDto.class);
			set = post.getCommentSet();
			commentDtoListcollect = set.stream().map(comment -> modelMapper.map(comment, CommentDto.class))
					.collect(Collectors.toList());
		}
		List<PostDto> collect = posts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		for (PostDto postDto : collect) {
			postDto.setUserDto(userDto);
			postDto.setCategoryDto(categotyDto);
			postDto.setCommentPostSet(commentDtoListcollect);
		}
		return collect;
		
	}

	@Override
	public List<PostDto> getPostByContent(String content, int pageNo, int pageSize) {
		User user = null;UserDto userDto = null;Category cat = null;
		CategoryDto categotyDto = null;Set<Comment> set = null;
		List<CommentDto> commentDtoListcollect = null;
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Posts> findByContentContaining = this.postRepository.findByContentContaining(content, pageable);
		List<Posts> posts = findByContentContaining.getContent();
		for (Posts post : posts) {
			user = post.getUser();
			userDto = this.modelMapper.map(user, UserDto.class);
			cat = post.getCategory();
			categotyDto = this.modelMapper.map(cat, CategoryDto.class);
			set = post.getCommentSet();
			commentDtoListcollect = set.stream().map(comment -> modelMapper.map(comment, CommentDto.class))
					.collect(Collectors.toList());
		}
		List<PostDto> collect = posts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		for (PostDto postDto : collect) {
			postDto.setUserDto(userDto);
			postDto.setCategoryDto(categotyDto);
			postDto.setCommentPostSet(commentDtoListcollect);
		}
		return collect;
		
	}

}
