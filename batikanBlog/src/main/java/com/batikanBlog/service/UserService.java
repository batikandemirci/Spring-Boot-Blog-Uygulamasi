package com.batikanBlog.service;

import java.util.List;

import com.batikanBlog.dto.UserDto;

public interface UserService{

    //add user
    UserDto addUser(UserDto userDto);

    //update user
    UserDto updateUser(UserDto userDto, int userId);

    //get single user
    UserDto getUser(int userId);

    //get all user
    List<UserDto> getAllUser();

    //delete user
    void deleteUser(int userId);

}
