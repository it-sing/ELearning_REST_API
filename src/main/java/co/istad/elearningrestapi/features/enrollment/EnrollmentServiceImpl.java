package co.istad.elearningrestapi.features.enrollment;

import co.istad.elearningrestapi.base.BaseMessage;
import co.istad.elearningrestapi.domain.Course;
import co.istad.elearningrestapi.domain.Enrollment;
import co.istad.elearningrestapi.domain.Student;
import co.istad.elearningrestapi.features.course.CourseRepository;
import co.istad.elearningrestapi.features.enrollment.dto.*;
import co.istad.elearningrestapi.features.student.StudentRepository;
import co.istad.elearningrestapi.mapper.EnrollmentMapper;
import co.istad.elearningrestapi.util.RandomUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EnrollmentServiceImpl implements EnrollmentService{
    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final EnrollmentMapper enrollmentMapper;

    @Override
    public void createNew(EnrollmentCreateRequest enrollmentCreateRequest) {
        Course course = courseRepository.findByAlias(enrollmentCreateRequest.courseAlias()).orElseThrow(
                ()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Course not found"
                )
        );
        Student student = studentRepository.findById(enrollmentCreateRequest.studentId()).orElseThrow(
                ()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Student not found"
                ));
        Enrollment enrollment = enrollmentMapper.fromEnrollmentCreateRequest(enrollmentCreateRequest);
        enrollment.setCode(RandomUtil.generate9Digits());
        enrollment.setEnrolledAt(LocalTime.now());
        enrollment.setCertified(false);
        enrollment.setDeleted(false);
        enrollment.setProgress(0);

        enrollment.setCourse(course);
        enrollment.setStudent(student);

        enrollmentRepository.save(enrollment);
    }

    @Override
    public List<EnrollmentResponse> findAllEnrollments(int page, int size, String sort, String code, String courseTitle, String courseCategory, String studentUsername, boolean isCertified) {
        if (page<0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Invalid page number,Page greater than or equal to zero");
        }
        if (size<1){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Invalid page size,Page size greater than or equal to one");
        }
        Specification<Enrollment> specification = Specification.where(null);
        if (code != null && !code.isEmpty()){
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("code"), code));
        }
        if (courseTitle!= null &&!courseTitle.isEmpty()){
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("courseTitle"), courseTitle));
        }
        if (courseCategory!= null &&!courseCategory.isEmpty()){
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("courseCategory"), courseCategory));
        }
        if (isCertified){
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("certified"), true));
        }

        Sort.Direction direction;
        String property;
        if (sort != null && sort.startsWith("date")) {
            String[] sortParts = sort.split(":");
            if (sortParts.length == 2 && sortParts[1].equalsIgnoreCase("desc")) {
                direction = Sort.Direction.DESC;
            } else {
                direction = Sort.Direction.ASC;
            }
            property = "enrolledAt"; // Assuming transaction date property name
        } else {

            direction = Sort.Direction.DESC;
            property = "enrolledAt";
        }
        Sort sortByEnrollmentAt = Sort.by(direction, property);
        PageRequest pageRequest = PageRequest.of(page, size, sortByEnrollmentAt);
        List<Enrollment> enrollments = enrollmentRepository.findAll(specification, pageRequest);
        return enrollmentMapper.toEnrollmentResponseList(enrollments);
    }

    @Override
    public EnrollmentDetailsResponse findEnrollmentDetailsByCode(String code) {
        Enrollment enrollment = enrollmentRepository.findByCode(code).orElseThrow(
                ()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Enrollment not found"
                ));
        return enrollmentMapper.toEnrollmentDetailsResponse(enrollment);

    }

    @Override
    public void updateProgressByCode(String code, EnrollmentProgressRequest enrollmentProgressRequest) {
        Enrollment enrollment = enrollmentRepository.findByCode(code).orElseThrow(
                ()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Enrollment not found"
                ));
        enrollmentMapper.fromEnrollmentProgressRequest(enrollment,enrollmentProgressRequest);
        enrollmentRepository.save(enrollment);
    }

    @Override
    public EnrollmentProgressResponse findProgressByCode(String code) {
        Enrollment enrollment = enrollmentRepository.findByCode(code).orElseThrow(
                ()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Enrollment not found"
                ));
        return enrollmentMapper.toEnrollmentProgressResponse(enrollment);
    }

    @Override
    public void updateCertifyByCode(String code) {
        Enrollment enrollment = enrollmentRepository.findByCode(code)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Enrollment not found with code"
                ));
        if (enrollment.getProgress() == 100){
            enrollment.setCertified(true);
            enrollmentRepository.save(enrollment);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Enrollment progress should be 100% to certify."
            );
        }
    }

    @Override
    public BaseMessage disableByCode(String code) {
        Enrollment enrollment = enrollmentRepository.findByCode(code).orElseThrow(
                ()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Enrollment not found"
                ));

        enrollment.setDeleted(true);
        return new BaseMessage("Enrollment is disabled");
    }


}
