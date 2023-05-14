package com.batikanBlog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private int id;
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Not a valid email")
    private String email;
    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 4, max = 11, message = "Password must be between 4 to 11 characters")
    private String password;
    @NotEmpty(message = "Please write something about yourself")
    private String about;
}
