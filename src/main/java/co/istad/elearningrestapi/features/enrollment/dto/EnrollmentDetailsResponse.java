package co.istad.elearningrestapi.features.enrollment.dto;

import co.istad.elearningrestapi.features.course.dto.CourseResponse;
import co.istad.elearningrestapi.features.student.dto.StudentResponse;

import java.time.LocalTime;

public record EnrollmentDetailsResponse(
        String code,
        LocalTime enrolledAt,
        boolean isDeleted,
        boolean isCertified,
        int progress,
        CourseResponse course,
        StudentResponse student
) {
}

