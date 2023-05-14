package com.batikanBlog.service.serviceImpl;

import com.batikanBlog.dto.CategoryDto;
import com.batikanBlog.dto.PostDto;
import com.batikanBlog.dto.UserDto;
import com.batikanBlog.exception.ResourceNotFoundException;
import com.batikanBlog.model.Category;
import com.batikanBlog.model.Post;
import com.batikanBlog.model.User;
import com.batikanBlog.repository.CategoryRepo;
import com.batikanBlog.repository.PostRepo;
import com.batikanBlog.repository.UserRepo;
import com.batikanBlog.service.PostService;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDto addPost(PostDto postDto,int userId,int categoryId) {
        log.info("inside add post service");
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        log.info(user.getName());
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
        Post post = this.modelMapper.map(postDto, Post.class);
        post.setUser(user);
        post.setCategory(category);
        Post save = this.postRepo.save(post);
        return this.modelMapper.map(save,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, int postId) {
        log.info("inside update post service");
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        Post save = this.postRepo.save(post);
        return this.modelMapper.map(save,PostDto.class);
    }

    @Override
    public void deletePost(int postId) {
        log.info("inside delete post service");
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
        this.postRepo.delete(post);
    }

    @Override
    public PostDto getPost(int postId) {
        log.info("inside get post service");
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
        return this.modelMapper.map(post,PostDto.class);
    }

    @Override
    public List<PostDto> getAllPost() {
        log.info("inside get all post service");
        List<Post> posts = this.postRepo.findAll();
        return posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getPostByUser(int userId) {
        log.info("inside get post by user service");
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        List<Post> posts = this.postRepo.findAllByUser(user);
        return posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getPostByCategory(int categoryId) {
        log.info("inside get post by category service");
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Id",categoryId));
        List<Post> posts = this.postRepo.findAllByCategory(category);
        return posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
    }
}
