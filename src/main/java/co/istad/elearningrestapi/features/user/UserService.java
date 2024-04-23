package co.istad.elearningrestapi.features.user;
import co.istad.elearningrestapi.features.user.dto.UserDetailsResponse;


import java.util.List;

public interface UserService {
    List<UserDetailsResponse> findAll(String sortById, String username, String email, String nationalIdCard, String phoneNumber, String name, String gender, String role, boolean isDelete);
    UserDetailsResponse findDetail(String username);
    void disableUser(String username);
    void enableUser(String username);
    void deleteUser(String username);

}
