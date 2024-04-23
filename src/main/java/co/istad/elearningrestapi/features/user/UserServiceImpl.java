package co.istad.elearningrestapi.features.user;

import co.istad.elearningrestapi.domain.User;
import co.istad.elearningrestapi.features.user.dto.UserDetailsResponse;
import co.istad.elearningrestapi.mapper.UserMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
//use log
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserDetailsResponse> findAll(String sortById, String username, String email, String nationalIdCard, String phoneNumber, String name, String gender, String role, boolean isDelete) {

        Sort sort = Sort.unsorted();
        if (sortById != null) {
            sort = Sort.by(sortById.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, "id");
        }
        List<User> users = userRepository.findUsers(sort, username, email, nationalIdCard, phoneNumber, name, gender, role, false);

        return userMapper.toUserResponse(users);
    }
    public UserDetailsResponse findDetail(String username) {

        List<User> users = userRepository.findByUsername(username);
        if (users.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "User not found"
            );

        } else {
            User user = users.get(0);

            return userMapper.toUserDetailsResponse(user);
        }
    }
    @Transactional
    @Override
    public void enableUser(String uuid) {

        List<User> user = userRepository.findByUsername(uuid);
        if (user.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "User not found"
            );

        }
        user.get(0).setIsDeleted(false);

        userRepository.save(user.get(0));
    }

    @Transactional
    @Override
    public void disableUser(String uuid) {

        List<User> user = userRepository.findByUsername(uuid);
        if (user.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "User not found"
            );

        }
        user.get(0).setIsDeleted(true);

        userRepository.save(user.get(0));
    }
    @Transactional
    @Override
    public void deleteUser(String username) {

        List<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "User not found"
            );

        }
        userRepository.delete(user.get(0));
    }

}