package co.istad.elearningrestapi.features.enrollment;

import co.istad.elearningrestapi.base.BaseMessage;
import co.istad.elearningrestapi.features.enrollment.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void createNew(@Valid @RequestBody EnrollmentCreateRequest enrollmentCreateRequest){
        enrollmentService.createNew(enrollmentCreateRequest);
    }
    @GetMapping
    List<EnrollmentResponse> findAll(@RequestParam(required = false,defaultValue = "0") int page,
                                     @RequestParam(required = false,defaultValue = "25") int size,
                                     @RequestParam(required = false) String sort,
                                     @RequestParam(required = false) String code,
                                     @RequestParam(required = false) String courseTitle,
                                     @RequestParam(required = false) String courseCategory,
                                     @RequestParam(required = false) String studentUsername,
                                     @RequestParam(required = false) boolean isCertified){
        return enrollmentService.findAllEnrollments(page, size, sort, code, courseTitle, courseCategory, studentUsername, isCertified);
    }

    @GetMapping("/{code}")
    EnrollmentDetailsResponse findEnrollmentDetailByCode(@PathVariable String code){
        return enrollmentService.findEnrollmentDetailsByCode(code);

    }
    @PutMapping("/{code}/progress")
    void updateEnrollment(@PathVariable String code, @Valid @RequestBody EnrollmentProgressRequest enrollmentProgressRequest){
        enrollmentService.updateProgressByCode(code, enrollmentProgressRequest);
    }
    @GetMapping("/{code}/progress")
    EnrollmentProgressResponse findProgressByCode(@PathVariable String code){
        return enrollmentService.findProgressByCode(code);
    }
    @PutMapping("/{code}/is-certified")
    void updateIsCertified(@PathVariable String code){
        enrollmentService.updateCertifyByCode(code);
    }
    @PutMapping("/{code}")
    BaseMessage disableByCode(@PathVariable String code){
        return enrollmentService.disableByCode(code);
    }
}
