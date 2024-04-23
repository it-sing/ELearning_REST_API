package co.istad.elearningrestapi.features.user;

import co.istad.elearningrestapi.features.user.dto.RoleAuthorityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;
    @GetMapping
    public ResponseEntity<List<RoleAuthorityResponse>> findAll() {

        return ResponseEntity.ok(roleService.findAll());
    }

    @GetMapping("/{name}")
    public ResponseEntity<RoleAuthorityResponse> findByName(
            @PathVariable String name) {

        return ResponseEntity.ok(roleService.findByName(name));
    }
}
