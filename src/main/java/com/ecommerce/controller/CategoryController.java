package com.ecommerce.controller;

import com.ecommerce.entity.Category;
import com.ecommerce.service.CategoryService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public Category addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }

    @GetMapping
    public List<Category> getAllCategories(){
        return  categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }
    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id , @RequestBody Category category){
        return categoryService.updateCategory(id , category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
    }

}
