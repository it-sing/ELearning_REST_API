package co.istad.elearningrestapi.features.enrollment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnrollmentCreateRequest(
        @NotBlank(message = "course is required")
        String courseAlias,

        @NotNull(message = "Enrollment student id required")
        Long studentId
) {
}