package co.istad.elearningrestapi.features.course.dto;

import co.istad.elearningrestapi.domain.Category;
import co.istad.elearningrestapi.domain.Instructor;
import jakarta.validation.constraints.NotBlank;

public record CourseCreateRequest(
        @NotBlank(message = "Alias is required")
        String alias,
        @NotBlank(message = "Thumbnail is required")
        String thumbnail,
        @NotBlank(message = "Title is required")
        String title,
        @NotBlank(message = "Description is required")
        String description,
        boolean isFree,
        Long instructorId,
        Long categoryId
) {
}

