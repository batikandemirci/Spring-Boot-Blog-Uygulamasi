package com.batikanBlog.controller;

import com.batikanBlog.dto.PostDto;
import com.batikanBlog.service.PostService;
import com.batikanBlog.utils.ApiResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("blog/posts")
@Slf4j
public class PostController {

    @Autowired
    private PostService postService;

    //Add post
    @PostMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<PostDto> addPost(@RequestBody PostDto postDto, @PathVariable int userId,@PathVariable int categoryId){
        log.info("inside add post controller");
        PostDto postDto1 = this.postService.addPost(postDto, userId, categoryId);
        return new ResponseEntity<>(postDto1, HttpStatus.CREATED);
    }

    //get all post
    @GetMapping("/")
    public ResponseEntity<List<PostDto>> getAllPost(){
        log.info("inside get all post controller");
        List<PostDto> allPost = this.postService.getAllPost();
        return new ResponseEntity<>(allPost,HttpStatus.OK);

    }

    //get post by user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDto>> getByUser(@PathVariable int userId){
        log.info("Inside get post by user controller");
        List<PostDto>posts = this.postService.getPostByUser(userId);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }

    //get post by category
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PostDto>> getByCategory(@PathVariable int categoryId){
        log.info("inside get post by category controller");
        List<PostDto> posts = this.postService.getPostByCategory(categoryId);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }

    //get post by id
    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable int postId){
        PostDto post = this.postService.getPost(postId);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }

    //update post
    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable int postId){
        PostDto postDto1 = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<>(postDto1,HttpStatus.OK);
    }

    //delete mapping
    @DeleteMapping("/{postId}")
    public  ResponseEntity<ApiResponse> deletePost(@PathVariable int postId){
        this.postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("Post deleted successfully",true),HttpStatus.OK);
    }

}
