package org.backend.smallecommerceapi.dto.product;

import lombok.Getter;
import lombok.Setter;
import org.backend.smallecommerceapi.dto.category.CategoryResponse;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private CategoryResponse category;
    private int quantity;
    private String imageUrl;
    private boolean active;
}
