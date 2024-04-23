package co.istad.elearningrestapi.features.student;

import co.istad.elearningrestapi.features.student.dto.StudentCreateRequest;
import co.istad.elearningrestapi.features.student.dto.StudentResponse;
import co.istad.elearningrestapi.features.student.dto.StudentUpdateRequest;
import org.springframework.data.domain.Page;

public interface StudentService {
    void createNew(StudentCreateRequest studentCreateRequest);
    Page<StudentResponse> findList(int page, int size);
    StudentResponse findStudentProfileByUsername(String username);
    StudentResponse updateStudentProfileByUsername(String username, StudentUpdateRequest studentUpdateRequest);
}
