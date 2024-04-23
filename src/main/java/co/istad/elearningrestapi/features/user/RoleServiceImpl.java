package co.istad.elearningrestapi.features.user;

import co.istad.elearningrestapi.domain.Role;
import co.istad.elearningrestapi.features.user.dto.RoleAuthorityResponse;
import co.istad.elearningrestapi.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service

@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Override
    public List<RoleAuthorityResponse> findAll() {

        List<Role> roles = roleRepository.findAll();
        return userMapper.toRoleResponses(roles);
    }

    @Override
    public RoleAuthorityResponse findByName(String name) {
        Role role = roleRepository.findByName(name);
        return Optional.ofNullable(role)
                .map(userMapper::toRoleResponse)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Role has not been found!"));
    }

}
