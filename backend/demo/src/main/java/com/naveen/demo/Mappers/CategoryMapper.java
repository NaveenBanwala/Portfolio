package com.naveen.demo.Mappers;


import com.naveen.demo.domain.PostStatus;
import com.naveen.demo.domain.dtos.CategoryDto;
import com.naveen.demo.domain.dtos.CreateCategoryRequest;
import com.naveen.demo.domain.entities.Category;
import com.naveen.demo.domain.entities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    @Mapping(target = "postCount" , source="posts" , qualifiedByName = "calculatePostCount")
    CategoryDto toDto(Category category);

    Category toEntity(CreateCategoryRequest createCategoryRequest);


    @Named("calculatePostCount")
    default long calculatePostCount(List<Post> posts) {
        if(posts==null || posts.isEmpty()) {
            return 0;

        }
        return posts.stream()
                .filter(post -> PostStatus.PUBLISHED.equals(post.getStatus()))
                .count();
    }



}
