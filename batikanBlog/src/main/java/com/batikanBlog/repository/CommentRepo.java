package com.batikanBlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.batikanBlog.model.Comment;
import com.batikanBlog.model.Post;
import com.batikanBlog.model.User;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment,Integer> {

    List<Comment> findByUser(User user);

    List<Comment> findByPost(Post post);

}
