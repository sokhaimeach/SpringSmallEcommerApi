package org.backend.smallecommerceapi.mapper;

import org.backend.smallecommerceapi.dto.product.ProductRequest;
import org.backend.smallecommerceapi.dto.product.ProductResponse;
import org.backend.smallecommerceapi.entity.Category;
import org.backend.smallecommerceapi.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = CategoryMapper.class
)
public interface ProductMapper {
    ProductResponse toResponse(Product product);

    @Mapping(target = "category", source = "category")
    Product toEntity(ProductRequest productRequest, Category category);
}
