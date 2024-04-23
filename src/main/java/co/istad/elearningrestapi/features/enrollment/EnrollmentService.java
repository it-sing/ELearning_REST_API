package co.istad.elearningrestapi.features.enrollment;

import co.istad.elearningrestapi.base.BaseMessage;
import co.istad.elearningrestapi.features.enrollment.dto.*;

import java.util.List;

public interface EnrollmentService {
    void createNew(EnrollmentCreateRequest enrollmentCreateRequest);
    List<EnrollmentResponse> findAllEnrollments(int page, int size, String sort,
                                                String code,
                                                String courseTitle,
                                                String courseCategory,
                                                String studentUsername,
                                                boolean isCertified);
    EnrollmentDetailsResponse findEnrollmentDetailsByCode(String code);
    void updateProgressByCode(String code, EnrollmentProgressRequest enrollmentProgressRequest);
    EnrollmentProgressResponse findProgressByCode(String code);
    void updateCertifyByCode(String code);
    BaseMessage disableByCode(String code);

}

