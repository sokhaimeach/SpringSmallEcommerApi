package org.backend.smallecommerceapi.dto.category;

import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

public class CategoryRequest {

    @NotEmpty
    @Length(min=1, max=100)
    private String name;

    private String description;
}
