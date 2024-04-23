package co.istad.elearningrestapi.features.student.dto;

import co.istad.elearningrestapi.features.user.dto.UserDetailsResponse;
import jakarta.validation.constraints.NotBlank;

public record StudentUpdateRequest(
        @NotBlank(message = "HighSchool is required")
        String highSchool,
        @NotBlank(message = "University is required")
        String university,
        UserDetailsResponse user
) {
}
