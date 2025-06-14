package com.naveen.demo.Services;

import com.naveen.demo.domain.dtos.CategoryDto;
import com.naveen.demo.domain.entities.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    List<Category> listCategories();

    Category createCategory(Category category);

    void deleteCategory(UUID id);
}
