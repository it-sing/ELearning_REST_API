package co.istad.elearningrestapi.mapper;

import co.istad.elearningrestapi.domain.Authority;
import co.istad.elearningrestapi.domain.Role;
import co.istad.elearningrestapi.domain.User;
import co.istad.elearningrestapi.features.user.dto.RoleAuthorityResponse;
import co.istad.elearningrestapi.features.user.dto.UserDetailsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
    List<UserDetailsResponse> toUserResponse(List<User> users);
    UserDetailsResponse toUserDetailsResponse(User user);


    RoleAuthorityResponse toRoleResponse(Role role);
    @Mapping(target = "roles", source = "user.roles")
    List<RoleAuthorityResponse> toRoleResponses(List<Role> roles);

    @Named("mapAuthorities")
    default List<String> mapAuthorities(List<Authority> authorities) {
        return authorities.stream()
                .map(Authority::getName)
                .collect(Collectors.toList());
    }
    default List<String> mapAuthorityNames(List<Authority> authorities) {
        return mapAuthorities(authorities);
    }
}
