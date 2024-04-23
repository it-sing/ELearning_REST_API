package co.istad.elearningrestapi.features.enrollment.dto;

import jakarta.validation.constraints.NotNull;

public record EnrollmentProgressRequest(
        @NotNull(message = "Progress is required")
        int progress
) {
}

