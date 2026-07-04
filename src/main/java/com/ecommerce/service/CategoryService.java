package com.ecommerce.service;

import com.ecommerce.entity.Category;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category addCategory(Category category){
        return categoryRepository.save(category);
    }
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }
    public Category getCategoryById(Long id){
        return categoryRepository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException(
                                "Category not found with id = "+ id
                        )
                        );
    }

    public Category updateCategory(Long id, Category category) {

        Category existing  = categoryRepository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException(
                                "Category not found with id = "+  id
                        ));

        existing.setName(category.getName());
        existing.setDescription(category.getDescription());

        return categoryRepository.save(existing);
    }
    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);

    }
}
