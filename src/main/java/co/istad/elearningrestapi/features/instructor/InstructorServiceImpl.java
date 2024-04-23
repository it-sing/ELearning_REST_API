package co.istad.elearningrestapi.features.instructor;

import co.istad.elearningrestapi.domain.Instructor;
import co.istad.elearningrestapi.domain.Role;
import co.istad.elearningrestapi.domain.User;
import co.istad.elearningrestapi.features.instructor.dto.InstructorCreateRequest;
import co.istad.elearningrestapi.features.instructor.dto.InstructorResponse;
import co.istad.elearningrestapi.features.instructor.dto.InstructorUpdateRequest;
import co.istad.elearningrestapi.features.user.RoleRepository;
import co.istad.elearningrestapi.features.user.UserRepository;
import co.istad.elearningrestapi.features.user.dto.UserDetailsResponse;
import co.istad.elearningrestapi.mapper.InstructorMapper;
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

@Service
@Slf4j
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService{

    private final InstructorRepository instructorRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final InstructorMapper instructorMapper;

    @Override
    public void createNew(InstructorCreateRequest instructorCreateRequest) {
        List<User> users = userRepository.findByUsername(instructorCreateRequest.username());

        if (users.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "User has not been found!"
            );
        }

        User user = users.get(0);

        Role instructorRole = roleRepository.findByName("INSTRUCTOR");
        if (instructorRole == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found");
        }

        user.setRoles(Collections.singletonList(instructorRole));

        Instructor instructor = instructorMapper.fromInstructorCreateRequest(instructorCreateRequest);
        instructor.setBlocked(false);
        instructor.setUser(user);

        instructorRepository.save(instructor);
    }

    @Override
    public Page<InstructorResponse> findList(int page, int size) {
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
        Page<Instructor> instructors = instructorRepository.findAll(pageable);

        return instructors.map(instructorMapper::toInstructorResponse);
    }

    @Override
    public InstructorResponse findInstructorProfileByUsername(String username) {
        Instructor instructor = instructorRepository.findByUsername(username)
                .orElseThrow(()->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Username is invalid"
                        ));
        return instructorMapper.toInstructorResponse(instructor);
    }

    @Override
    public InstructorResponse updateInstructorProfileByUsername(String username, InstructorUpdateRequest instructorUpdateRequest) {
        // Retrieve the student by username
        Instructor instructor = instructorRepository.findByUsername(username)
                .orElseThrow(()->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Instructor with username '" + username + "' not found"
                        ));

        // Update the student's profile fields
        instructor.setBiography(instructorUpdateRequest.biography());
        instructor.setGithub(instructorUpdateRequest.github());
        instructor.setJobTitle(instructorUpdateRequest.jobTitle());
        instructor.setLinkedIn(instructorUpdateRequest.linkedIn());
        instructor.setWebsite(instructorUpdateRequest.website());

        // Update the associated user details
        User user = instructor.getUser();
        UserDetailsResponse userDetailsResponse = instructorUpdateRequest.user();
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
        instructor = instructorRepository.save(instructor);

        // Map the updated student to StudentResponse DTO
        return instructorMapper.toInstructorResponse(instructor);
    }
}
