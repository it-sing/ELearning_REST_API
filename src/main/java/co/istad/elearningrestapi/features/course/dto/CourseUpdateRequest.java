package co.istad.elearningrestapi.features.course.dto;

import jakarta.validation.constraints.NotBlank;

public record CourseUpdateRequest(
        @NotBlank(message = "Alias is required")
        String alias,
        @NotBlank(message = "Thumbnail URL is required")
        String thumbnail,
        @NotBlank(message = "Title is required")
        String title,
        @NotBlank(message = "Description is required")
        String description,
        boolean isFree
) {
}

