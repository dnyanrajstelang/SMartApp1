package com.smart.service;

import com.smart.entity.Category;
import com.smart.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Page<Category> getAllCategories(int page) {
        return categoryRepository.findAll(PageRequest.of(page, 10));
    }

    public String createCategory(Category category) {
        if (category.getName() == null || category.getName().isEmpty()) {
            return "Category name cannot be null or empty.";
        }
        if (categoryRepository.existsByName(category.getName())) {
            return "Category name already exists.";
        }
        categoryRepository.save(category);
        return "Category created successfully.";
    }
    

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public String updateCategory(Long id, Category category) {
        Optional<Category> existingCategory = categoryRepository.findById(id);
        if (existingCategory.isPresent()) {
            if (categoryRepository.existsByName(category.getName())) {
                return "Category name already exists.";
            }
            Category updatedCategory = existingCategory.get();
            updatedCategory.setName(category.getName());
            categoryRepository.save(updatedCategory);
            return "Category updated successfully.";
        } else {
            return "Category not found.";
        }
    }

    public String deleteCategory(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return "Category deleted successfully.";
        }
        return "Category not found.";
    }
}

