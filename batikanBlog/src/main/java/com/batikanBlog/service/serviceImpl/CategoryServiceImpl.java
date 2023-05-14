package com.batikanBlog.service.serviceImpl;

import com.batikanBlog.dto.CategoryDto;
import com.batikanBlog.exception.ResourceNotFoundException;
import com.batikanBlog.exception.UserExistsException;
import com.batikanBlog.model.Category;
import com.batikanBlog.repository.CategoryRepo;
import com.batikanBlog.service.CategoryService;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        log.info("inside add category service");
        Category byTitle = this.categoryRepo.findByTitle(categoryDto.getTitle());
        if(byTitle!=null){
            throw new UserExistsException("Category already exists");
        }
        Category category = this.modelMapper.map(categoryDto, Category.class);
        Category save = this.categoryRepo.save(category);
        return this.modelMapper.map(save,CategoryDto.class);
    }

    @Override
    public CategoryDto getCategory(int categoryId) {
        log.info("inside get category service");
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
        return this.modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        log.info("Inside get all category service");
        List<Category> all = this.categoryRepo.findAll();
        return all.stream().map((category) -> this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) {
        log.info("inside update category service");
//        Category byTitle = this.categoryRepo.findByTitle(categoryDto.getTitle());
//        if(byTitle!=null){
//            throw new UserExistsException("Category already exists");
//        }
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        Category save = this.categoryRepo.save(category);
        return this.modelMapper.map(save,CategoryDto.class);
    }

    @Override
    public void deleteCategory(int categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
        this.categoryRepo.delete(category);
    }
}
