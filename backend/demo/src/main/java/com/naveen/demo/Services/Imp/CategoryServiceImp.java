package com.naveen.demo.Services.Imp;

import com.naveen.demo.Services.CategoryService;
import com.naveen.demo.domain.entities.Category;
import com.naveen.demo.repositries.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CategoryServiceImp implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
     public List<Category> listCategories(){
        return categoryRepository.findAllWithPostCount();
    };

}
