package co.istad.elearningrestapi.features.instructor.dto;

import co.istad.elearningrestapi.features.user.dto.UserDetailsResponse;
import jakarta.validation.constraints.NotBlank;

public record InstructorUpdateRequest(
        @NotBlank(message = "Biography is required")
        String biography,
        String github,
        @NotBlank(message = "Job Title is required")
        String jobTitle,
        String linkedIn,
        String website,
        UserDetailsResponse user
) {
}
