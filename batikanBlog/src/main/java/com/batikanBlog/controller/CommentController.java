package com.batikanBlog.controller;

import com.batikanBlog.dto.CommentDto;
import com.batikanBlog.service.CommentService;
import com.batikanBlog.utils.ApiResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("blog/comment")
@Slf4j
public class CommentController {

    @Autowired
    private CommentService commentService;

    //add comment
    @PostMapping("/post/{postId}/user/{userId}")
    public ResponseEntity<CommentDto> addComment(@RequestBody CommentDto commentDto,@PathVariable(name="postId") int postId,@PathVariable(name="userId") int userId){
        CommentDto commentDto1 = this.commentService.addComment(commentDto, postId, userId);
        return new ResponseEntity<>(commentDto1, HttpStatus.CREATED);
    }

    //get comment by user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CommentDto>> getCommentByUser(@PathVariable int userId){
        List<CommentDto> commentByUser = this.commentService.getCommentByUser(userId);
        return new ResponseEntity<>(commentByUser,HttpStatus.OK);
    }

    //get comment by post
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentDto>> getCommentByPost(@PathVariable int postId){
        log.info("inside get comment bt post controller");
        List<CommentDto> commentByPost = this.commentService.getCommentByPost(postId);
        return new ResponseEntity<>(commentByPost,HttpStatus.OK);
    }

    //delete post
    @DeleteMapping("/{commentId}")
    public ResponseEntity<ApiResponse> deleteMapping(@PathVariable int commentId){
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("Comment deleted successfully",true),HttpStatus.OK);
    }

}
