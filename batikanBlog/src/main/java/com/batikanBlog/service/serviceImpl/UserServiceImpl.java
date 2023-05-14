package com.batikanBlog.service.serviceImpl;

import com.batikanBlog.dto.UserDto;
import com.batikanBlog.exception.ResourceNotFoundException;
import com.batikanBlog.exception.UserExistsException;
import com.batikanBlog.model.User;
import com.batikanBlog.repository.UserRepo;
import com.batikanBlog.service.UserService;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto addUser(UserDto userDto) {
        log.info("inside add user service");
        Optional<User> byEmail = this.userRepo.findByEmail(userDto.getEmail());
        if(byEmail!=null){
            throw new UserExistsException("Email already exists");
        }
        User user = this.modelMapper.map(userDto,User.class);
        User save = this.userRepo.save(user);
        log.info("user saved in db(inside user service)");
        UserDto map = this.modelMapper.map(save, UserDto.class);
        return map;
    }

    @Override
    public UserDto updateUser(UserDto userDto, int userId) {
        log.info("inside update user service");
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAbout(user.getAbout());
        user.setPassword(userDto.getPassword());
        User save = this.userRepo.save(user);
        UserDto map = this.modelMapper.map(save, UserDto.class);
        log.info("User updated in user update service");
        return map;
    }

    @Override
    public UserDto getUser(int userId) {
        log.info("Inside get user service");
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        UserDto map = this.modelMapper.map(user, UserDto.class);
        log.info("Fetched user from user service");
        return map;
    }

    @Override
    public List<UserDto> getAllUser() {
        log.info("Inside get all user service");
        List<User> users = this.userRepo.findAll();
        List<UserDto> userDtos = users.stream().map((user) -> this.modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
        log.info("fetched all user");
        return userDtos;
    }

    @Override
    public void deleteUser(int userId) {
        log.info("inside user delete service");
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        this.userRepo.delete(user);
    }
}
