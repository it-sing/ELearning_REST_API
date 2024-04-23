package co.istad.elearningrestapi.features.user;

import co.istad.elearningrestapi.features.user.dto.RoleAuthorityResponse;
import co.istad.elearningrestapi.features.user.dto.RoleResponse;

import java.util.List;

public interface RoleService {
    List<RoleAuthorityResponse> findAll();
    RoleAuthorityResponse findByName(String name);

}
