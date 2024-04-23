package co.istad.elearningrestapi.features.instructor;

import co.istad.elearningrestapi.features.instructor.dto.InstructorCreateRequest;
import co.istad.elearningrestapi.features.instructor.dto.InstructorResponse;
import co.istad.elearningrestapi.features.instructor.dto.InstructorUpdateRequest;
import org.springframework.data.domain.Page;

public interface InstructorService {
    void createNew(InstructorCreateRequest instructorCreateRequest);
    Page<InstructorResponse> findList(int page, int size);
    InstructorResponse findInstructorProfileByUsername(String username);
    InstructorResponse updateInstructorProfileByUsername(String username, InstructorUpdateRequest instructorUpdateRequest);
}
