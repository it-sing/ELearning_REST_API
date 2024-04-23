package co.istad.elearningrestapi.features.student;

import co.istad.elearningrestapi.features.student.dto.StudentCreateRequest;
import co.istad.elearningrestapi.features.student.dto.StudentResponse;
import co.istad.elearningrestapi.features.student.dto.StudentUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    //Create a new student
    @PostMapping
    void createNew(@Valid @RequestBody StudentCreateRequest studentCreateRequest) {
        studentService.createNew(studentCreateRequest);
    }

    //Find all students by pagination
    @GetMapping
    Page<StudentResponse> findList(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "25") int size
    ){
        return studentService.findList(page, size);
    }

    //Find a student’s profile
    @GetMapping("/{username}")
    StudentResponse findStudentProfileByUsername(@PathVariable String username) {
        return studentService.findStudentProfileByUsername(username);
    }

    //Update a student’s profile
    @PutMapping("/{username}")
    StudentResponse updateStudentProfileByUsername(@PathVariable String username,@Valid @RequestBody StudentUpdateRequest studentUpdateRequest) {
        return studentService.updateStudentProfileByUsername(username, studentUpdateRequest);
    }

}
