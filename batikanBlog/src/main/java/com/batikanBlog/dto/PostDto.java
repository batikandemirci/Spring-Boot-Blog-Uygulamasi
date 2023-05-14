package com.batikanBlog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    @NotEmpty(message = "Title cannot be empty")
    private String title;
    private String content;
    private UserDto user;
    private CategoryDto category;
    private Set<CommentDto> comments = new HashSet<>();

}
