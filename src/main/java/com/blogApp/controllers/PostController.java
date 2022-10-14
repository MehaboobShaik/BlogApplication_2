package com.blogApp.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blogApp.config.PostResponse;
import com.blogApp.customexceptions.ApiResponse;
import com.blogApp.modelDto.PostDto;
import com.blogApp.models.Posts;
import com.blogApp.services.FileService;
import com.blogApp.services.PostService;


@RestController
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@PostMapping("/createpost/user/{userId}/category/{categoryId}")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto ,
			                                  @PathVariable Integer userId,
			                                  @PathVariable Integer categoryId){
		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
		
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/updatepost/{postId}")
	public ResponseEntity<PostDto> UpdatePost(@RequestBody PostDto postDto , @PathVariable Integer postId){
		 PostDto updatePost = this.postService.UpdatePost(postDto,postId);
		 return new ResponseEntity<PostDto>(updatePost,HttpStatus.CREATED);
		
	}
	@DeleteMapping("/deletepost/{postId}/user/{userId}/category/{categoryId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId,
                                                  @PathVariable Integer userId,
                                                  @PathVariable Integer categoryId){
		System.out.println(postId+" "+userId+" "+categoryId);
		this.postService.DeletePost(postId,userId,categoryId);
		ApiResponse apiResponse = new ApiResponse("post deleted sucessfully",false);
		 return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.FOUND);
		
	}
	@GetMapping("/getpost/{postId}")
	public ResponseEntity<PostDto> getpost(@PathVariable Integer postId){
		 PostDto post = this.postService.getPost(postId);
		 return new ResponseEntity<PostDto>(post,HttpStatus.FOUND);
		
	}
	@GetMapping("/getAllposts")
	public ResponseEntity<PostResponse> getAllposts(@RequestParam(value = "pageNo", defaultValue = "10") Integer pageNo,
			                                         @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
		PostResponse allPosts = this.postService.getAllPosts(pageNo,pageSize);
		 return new ResponseEntity<PostResponse>(allPosts,HttpStatus.FOUND);
		
	}
	
	@GetMapping("/getPostsByUser/{userId}")
	public ResponseEntity<List<PostDto>> getAllpostsByUser(@PathVariable Integer userId,
			                                               @RequestParam(value = "pageNo", defaultValue = "10") Integer pageNo,
			                                               @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        List<PostDto> postByUser = this.postService.getPostByUser(userId, pageNo, pageSize);   
		return new ResponseEntity<List<PostDto>>(postByUser,HttpStatus.FOUND);
		
	}
	@GetMapping("/getPostsByCategory/{categoryId}")
	public ResponseEntity<List<PostDto>> getAllpostsByCategory(@PathVariable Integer categoryId,
			                                               @RequestParam(value = "pageNo", defaultValue = "10") Integer pageNo,
			                                               @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        List<PostDto> postByUser = this.postService.getPostByCategory(categoryId, pageNo, pageSize);   
		return new ResponseEntity<List<PostDto>>(postByUser,HttpStatus.FOUND);
		
	}
	@GetMapping("/getPostsByTitle/{title}")
	public ResponseEntity<List<PostDto>> getAllpostsByTitle(@PathVariable String title,
			                                               @RequestParam(value = "pageNo", defaultValue = "10") Integer pageNo,
			                                               @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        List<PostDto> postByUser = this.postService.getPostByTitle(title, pageNo, pageSize);   
		return new ResponseEntity<List<PostDto>>(postByUser,HttpStatus.FOUND);
		
	}
	@GetMapping("/getPostsByContent/{content}")
	public ResponseEntity<List<PostDto>> getAllpostsByContent(@PathVariable String content,
			                                               @RequestParam(value = "pageNo", defaultValue = "10") Integer pageNo,
			                                               @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        List<PostDto> postByUser = this.postService.getPostByContent(content, pageNo, pageSize);   
		return new ResponseEntity<List<PostDto>>(postByUser,HttpStatus.FOUND);
		
	}
	
	@Value("${project.image}")
	private String path;
	
	@PostMapping("/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadImage(@PathVariable Integer postId,@RequestParam("image") MultipartFile image) throws IOException{
		
		PostDto postDto = postService.getPost(postId);
		String uploadImage = fileService.UploadImage(path, image);
		postDto.setImage(uploadImage);
		PostDto updatePost = postService.UpdatePost(postDto, postId);
		
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.CREATED);
		
	}
	
	@GetMapping(value = "/image/{imageName}" , produces = MediaType.IMAGE_JPEG_VALUE)
	public void getImage(@PathVariable("imageName") String imageName,HttpServletResponse response) throws IOException {
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
}
