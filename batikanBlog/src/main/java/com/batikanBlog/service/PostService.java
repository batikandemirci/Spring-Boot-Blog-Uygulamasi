package com.batikanBlog.service;

import com.batikanBlog.dto.PostDto;
import com.batikanBlog.repository.PostRepo;

import java.util.List;

public interface PostService{

    //create post
    PostDto addPost(PostDto postDto,int userId,int categoryId);

    //update post
    PostDto updatePost(PostDto postDto, int postId);

    //delete post
    void deletePost(int postId);

    //get post
    PostDto getPost(int postId);

    //get all post
    List<PostDto> getAllPost();

    //get post by user
    List<PostDto> getPostByUser(int userId);

    //get post by category
    List<PostDto> getPostByCategory(int categoryId);

}
