package co.istad.elearningrestapi.features.enrollment.dto;

import java.time.LocalTime;

public record EnrollmentResponse(
        String code,
        LocalTime enrolledAt,
        boolean isDeleted,
        boolean isCertified,
        int progress,
        Long studentId,
        String courseAlias
) {
}

