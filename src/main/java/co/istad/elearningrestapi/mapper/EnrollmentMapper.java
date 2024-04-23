package co.istad.elearningrestapi.mapper;

import co.istad.elearningrestapi.domain.Enrollment;
import co.istad.elearningrestapi.features.enrollment.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {


    Enrollment fromEnrollmentCreateRequest(EnrollmentCreateRequest enrollmentCreateRequest);

    @Mapping(target = "courseAlias", source = "course.alias")
    @Mapping(target = "studentId", source = "student.id")
    EnrollmentResponse toEnrollmentResponse(Enrollment enrollment);

    List<EnrollmentResponse> toEnrollmentResponseList(List<Enrollment> enrollments);

    @Mapping(target = "course", source = "course")
    @Mapping(target = "student", source = "student")
    EnrollmentDetailsResponse toEnrollmentDetailsResponse(Enrollment enrollment);


    void fromEnrollmentProgressRequest(@MappingTarget Enrollment enrollment, EnrollmentProgressRequest enrollmentProgressRequest);

    EnrollmentProgressResponse toEnrollmentProgressResponse(Enrollment enrollment);
}

