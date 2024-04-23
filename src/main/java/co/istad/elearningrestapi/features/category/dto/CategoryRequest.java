package co.istad.elearningrestapi.features.category.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(
        @NotBlank(message = "Alias is required")
        String alias,
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Icon is required")
        String icon,
        Long parentCategory
) {
}
