package co.istad.elearningrestapi.features.student.dto;

import jakarta.validation.constraints.NotBlank;

public record StudentCreateRequest(
        @NotBlank(message = "Username is required")
        String username,
        @NotBlank(message = "HighSchool is required")
        String highSchool,
        @NotBlank(message = "University is required")
        String university
) {
}
