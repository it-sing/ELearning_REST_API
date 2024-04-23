package co.istad.elearningrestapi.features.course.dto;

public record CourseResponse(
        String alias,
        String thumbnail,
        String title,
        String description,
        boolean isFree,
        Long categoryId
) {
}

