package com.batikanBlog.service;

import java.util.List;

import com.batikanBlog.dto.CommentDto;

public interface CommentService {

    //add comment
    CommentDto addComment(CommentDto commentDto,int postId,int userId);

    //delete comment
    void deleteComment(int commentId);

    //get comment by user
    List<CommentDto> getCommentByUser(int userId);

    //get comment by post
    List<CommentDto> getCommentByPost(int postId);

}
