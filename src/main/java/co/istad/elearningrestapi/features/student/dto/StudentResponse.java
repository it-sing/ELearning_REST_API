package co.istad.elearningrestapi.features.student.dto;

import co.istad.elearningrestapi.features.user.dto.UserDetailsResponse;

public record StudentResponse(
        String highSchool,
        String university,
        UserDetailsResponse user
) {
}
