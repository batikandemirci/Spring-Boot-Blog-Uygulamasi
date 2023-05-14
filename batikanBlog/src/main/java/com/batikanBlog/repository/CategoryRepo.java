package com.batikanBlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.batikanBlog.model.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer> {

    Category findByTitle(String title);

}
