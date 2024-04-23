package co.istad.elearningrestapi.features.instructor.dto;

import co.istad.elearningrestapi.features.user.dto.UserDetailsResponse;
import jakarta.validation.constraints.NotBlank;

public record InstructorResponse(
        String biography,
        String github,
        String jobTitle,
        String linkedIn,
        String website,
        UserDetailsResponse user
) {
}
