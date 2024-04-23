package co.istad.elearningrestapi.mapper;

import co.istad.elearningrestapi.domain.Student;
import co.istad.elearningrestapi.features.student.dto.StudentCreateRequest;
import co.istad.elearningrestapi.features.student.dto.StudentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    Student fromStudentCreateRequest(StudentCreateRequest studentCreateRequest);

    @Mapping(target = "user",source = "user")
    StudentResponse toStudentResponse(Student student);
}
