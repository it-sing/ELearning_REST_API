package co.istad.elearningrestapi.features.user;

import co.istad.elearningrestapi.features.user.dto.UserDetailsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDetailsResponse>> findAll(
            @RequestParam(required = false) String sortById,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String nationalIdCard,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String role,
            @RequestParam(required = false) boolean isDelete
    ) {
        List<UserDetailsResponse> users = userService.findAll(sortById,
                username, email, nationalIdCard, phoneNumber, name,
                gender, role, isDelete );

        return ResponseEntity.ok(users);
    }
    @GetMapping("/{username}")
    public ResponseEntity<UserDetailsResponse> findDetail(@PathVariable String username) {

        UserDetailsResponse user = userService.findDetail(username);
        return ResponseEntity.ok(user);
    }
    @PutMapping("/{username}/disable")
    public ResponseEntity<String> disableUser(@PathVariable String username) {

        userService.disableUser(username);
        return ResponseEntity.status(HttpStatus.OK).body("User disabled successfully");
    }

    @PutMapping("/{username}/enable")
    public ResponseEntity<String> enableUser(@PathVariable String username) {

        userService.enableUser(username);
        return ResponseEntity.status(HttpStatus.OK).body("User enabled successfully");
    }
    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {

        userService.deleteUser(username);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
    }
}
