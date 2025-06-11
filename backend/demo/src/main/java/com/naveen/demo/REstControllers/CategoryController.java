package com.naveen.demo.REstControllers;

import com.naveen.demo.Mappers.CategoryMapper;
import com.naveen.demo.Services.CategoryService;
import com.naveen.demo.domain.dtos.CategoryDto;
import com.naveen.demo.domain.dtos.CreateCategoryRequest;
import com.naveen.demo.domain.entities.Category;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> listCategories(){
        List<CategoryDto> categories = categoryService.listCategories()
                .stream().map(categoryMapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(categories);

    }
    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody @Valid CreateCategoryRequest  createCategoryRequest){
        Category categoryToCreate = categoryMapper.toEntity(createCategoryRequest);

       Category saveCategory = categoryService.createCategory(categoryToCreate);
//       return ResponseEntity.ok(categoryMapper.toDto(saveCategory));
        return new ResponseEntity<>(
                categoryMapper.toDto(saveCategory),
                HttpStatus.CREATED

        );

    }
    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id){
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }



}
