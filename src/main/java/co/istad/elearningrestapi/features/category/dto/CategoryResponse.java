package co.istad.elearningrestapi.features.category.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryResponse(
        String alias,
        String name,
        String icon
) {
}
