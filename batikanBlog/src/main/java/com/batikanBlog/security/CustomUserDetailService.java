package com.batikanBlog.security;

import com.batikanBlog.exception.UserDoesNotExistsException;
import com.batikanBlog.model.User;
import com.batikanBlog.repository.UserRepo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("inside load user by user name function");
        User user = this.userRepo.findByEmail(username).orElseThrow(() -> new UserDoesNotExistsException("Bad Credentials !!!"));
        log.info(user.getName());
        Set<SimpleGrantedAuthority> authorities = user
                .getRoles()
                .stream()
                .map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
        Set<SimpleGrantedAuthority> authorities1 = new HashSet<>();
        log.info("leaving custom user detail service");
        return new org.springframework.security.core.userdetails.User(username,user.getPassword(),authorities);
    }
}
