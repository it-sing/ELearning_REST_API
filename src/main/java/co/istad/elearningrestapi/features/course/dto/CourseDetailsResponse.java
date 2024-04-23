package co.istad.elearningrestapi.features.course.dto;

import co.istad.elearningrestapi.features.category.dto.CategoryResponse;
import co.istad.elearningrestapi.features.instructor.dto.InstructorResponse;

public record CourseDetailsResponse(
        String alias,
        String thumbnail,
        String title,
        String description,
        boolean isFree,
        InstructorResponse instructor,
        CategoryResponse category
) {
}

