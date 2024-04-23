package co.istad.elearningrestapi.features.student;

import co.istad.elearningrestapi.domain.Role;
import co.istad.elearningrestapi.domain.Student;
import co.istad.elearningrestapi.domain.User;
import co.istad.elearningrestapi.features.student.dto.StudentCreateRequest;
import co.istad.elearningrestapi.features.student.dto.StudentResponse;
import co.istad.elearningrestapi.features.student.dto.StudentUpdateRequest;
import co.istad.elearningrestapi.features.user.RoleRepository;
import co.istad.elearningrestapi.features.user.UserRepository;
import co.istad.elearningrestapi.features.user.dto.UserDetailsResponse;
import co.istad.elearningrestapi.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final StudentMapper studentMapper;

    @Override
    public void createNew(StudentCreateRequest studentCreateRequest) {
        List<User> users = userRepository.findByUsername(studentCreateRequest.username());

        if (users.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User has not been found!");
        }

        User user = users.get(0);

        Role studentRole = roleRepository.findByName("STUDENT");
        if (studentRole == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found");
        }

        user.setRoles(Collections.singletonList(studentRole));

        Student student = studentMapper.fromStudentCreateRequest(studentCreateRequest);
        student.setIsBlocked(false);
        student.setUser(user);

        studentRepository.save(student);
    }

    @Override
    public Page<StudentResponse> findList(int page, int size) {
        //validate page and size
        if (page < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Page number must be greater than or equal to zero"
            );
        }
        if (size < 1) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Page size must be greater than or equal to one"
            );
        }
        Pageable pageable = PageRequest.of(page, size);
        Page<Student> students = studentRepository.findAll(pageable);

        return students.map(studentMapper::toStudentResponse);
    }

    @Override
    public StudentResponse findStudentProfileByUsername(String username) {
        Student student = studentRepository.findByUsername(username)
                .orElseThrow(()->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Username is invalid"
                        ));
        return studentMapper.toStudentResponse(student);
    }

    @Override
    public StudentResponse updateStudentProfileByUsername(String username, StudentUpdateRequest studentUpdateRequest) {
        // Retrieve the student by username
        Student student = studentRepository.findByUsername(username)
                .orElseThrow(()->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Student with username '" + username + "' not found"
                        ));

        // Update the student's profile fields
        student.setHighSchool(studentUpdateRequest.highSchool());
        student.setUniversity(studentUpdateRequest.university());

        // Update the associated user details
        User user = student.getUser();
        UserDetailsResponse userDetailsResponse = studentUpdateRequest.user();
        user.setAddress1(userDetailsResponse.address1());
        user.setAddress2(userDetailsResponse.address2());
        user.setEmail(userDetailsResponse.email());
        String dobString = userDetailsResponse.dob();
        LocalDate dob = LocalDate.parse(dobString);
        user.setDob(dob);
        user.setFamilyName(userDetailsResponse.familyName());
        user.setGender(userDetailsResponse.gender());
        user.setGivenName(userDetailsResponse.givenName());

        // Save the updated student entity
        student = studentRepository.save(student);

        // Map the updated student to StudentResponse DTO
        return studentMapper.toStudentResponse(student);
    }

}
