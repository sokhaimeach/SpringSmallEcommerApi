package org.backend.smallecommerceapi.mapper;

import org.backend.smallecommerceapi.dto.category.CategoryRequest;
import org.backend.smallecommerceapi.dto.category.CategoryResponse;
import org.backend.smallecommerceapi.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponse toResponse(Category category);
    Category toEntity(CategoryRequest categoryRequest);
}
