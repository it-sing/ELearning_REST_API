package co.istad.elearningrestapi.features.course;

import co.istad.elearningrestapi.domain.Category;
import co.istad.elearningrestapi.domain.Course;
import co.istad.elearningrestapi.domain.Instructor;
import co.istad.elearningrestapi.features.category.CategoryRepository;
import co.istad.elearningrestapi.features.course.dto.*;
import co.istad.elearningrestapi.features.instructor.InstructorRepository;
import co.istad.elearningrestapi.mapper.CourseMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{

    private final InstructorRepository instructorRepository;
    private final CategoryRepository categoryRepository;
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    @Override
    public void createNew(CourseCreateRequest courseCreateRequest) {
        // Check if the instructor exists and isn't blocked
        Instructor instructor = instructorRepository.findById(courseCreateRequest.instructorId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Instructor has not been found"));

        if (instructor.isBlocked()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Instructor is blocked"
            );
        }

        // Retrieve the category from the database
        Category category = categoryRepository.findById(courseCreateRequest.categoryId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category has not been found"));

        Course course = new Course();
        course.setAlias(courseCreateRequest.alias());
        course.setDescription(courseCreateRequest.description());
        course.setFree(courseCreateRequest.isFree());
        course.setThumbnail(courseCreateRequest.thumbnail());
        course.setTitle(courseCreateRequest.title());
        course.setInstructor(instructor);
        course.setCategory(category);

        courseRepository.save(course);
    }

    @Override
    public Page<CourseResponse> findList(int page, int size) {
        // Validate page and size
        if (page < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Page number must be greater than or equal to zero"
            );
        }
        if (size < 1) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Page size must be greater than or equal to one"
            );
        }

        Pageable pageable = PageRequest.of(page, size);
        Page<Course> courses = courseRepository.findByIsDeletedFalse(pageable);

        return courses.map(courseMapper::toCourseResponse);
    }

    @Override
    public CourseDetailsResponse findCourseByAlias(String alias) {
        Course course = courseRepository.findByAliasAndIsDeletedFalse(alias)
                .orElseThrow(()->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Course alias name is invalid"
                        ));
        return courseMapper.toCourseDetailResponse(course);
    }

    @Override
    public CourseResponse updateCourseByAlias(String alias, CourseUpdateRequest courseUpdateRequest) {
        Course course = courseRepository.findByAlias(alias)
                .orElseThrow(()->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Category alias name is invalid"
                        ));
        course.setAlias(courseUpdateRequest.alias());
        course.setTitle(courseUpdateRequest.title());
        course.setThumbnail(courseUpdateRequest.thumbnail());
        course.setDescription(courseUpdateRequest.description());
        course.setFree(courseUpdateRequest.isFree());
        course = courseRepository.save(course);

        return courseMapper.toCourseResponse(course);
    }

    @Override
    public CourseResponse updateCourseThumbnail(String alias, CourseThumbnailRequest courseThumbnailRequest) {
        Course course = courseRepository.findByAlias(alias)
                .orElseThrow(()->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Category alias name is invalid"
                        ));
        course.setThumbnail(courseThumbnailRequest.thumbnail());
        course = courseRepository.save(course);

        return courseMapper.toCourseResponse(course);
    }

    @Override
    public CourseResponse updateCourseCategory(String alias, CourseCategoryRequest courseCategoryRequest) {
        // Find the course by alias
        Course course = courseRepository.findByAlias(alias)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Course alias is invalid"
                ));

        // Find the category by ID provided in the request body
        Category category = categoryRepository.findById(courseCategoryRequest.categoryId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category not found"
                ));

        // Assign the category to the course
        course.setCategory(category);

        // Save the updated course
        course = courseRepository.save(course);

        // Map the updated course to a CourseResponse DTO
        return courseMapper.toCourseResponse(course);
    }

    @Transactional
    @Override
    public void disableCourseByAlias(String alias) {
        if (!courseRepository.existsByAlias(alias)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Course has not been found"
            );
        }
        try {
            courseRepository.disableCourseByAlias(alias);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Something went wrong"
            );
        }
    }
}

