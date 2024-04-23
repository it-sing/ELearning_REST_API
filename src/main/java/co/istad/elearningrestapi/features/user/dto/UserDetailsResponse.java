package co.istad.elearningrestapi.features.user.dto;

import java.util.List;

public record UserDetailsResponse(
        Long id,
        String uuid,
        String username,
        String givenName,
        String gender,
        String email,
        String phoneNumber,
        String nationalIdCard,
        String familyName,
        String address1,
        String address2,
        String profile,
        String  dob,
        List<RoleResponse> roles

) {
}
