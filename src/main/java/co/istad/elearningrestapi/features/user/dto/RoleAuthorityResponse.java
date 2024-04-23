package co.istad.elearningrestapi.features.user.dto;

import java.util.List;

public record RoleAuthorityResponse(
       String name,
       List<String> authorities

) {

}
