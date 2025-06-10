package com.naveen.demo.Services;

import com.naveen.demo.domain.dtos.CategoryDto;
import com.naveen.demo.domain.entities.Category;

import java.util.List;

public interface CategoryService {

    List<Category> listCategories();
}
