package com.example.koolcard.services;

import com.example.koolcard.entities.Category;
import java.util.List;

public interface CategoryInterface {
    Category addCategory(Category category);
    List<Category> getAllCategories();
    Category getCategoryById(Long id);
    Category updateCategory(Long id, Category updatedCategory);
    void deleteCategory(Long id);
}
