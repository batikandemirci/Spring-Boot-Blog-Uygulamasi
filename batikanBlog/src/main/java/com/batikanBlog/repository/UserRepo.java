package com.batikanBlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.batikanBlog.model.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);

}
