package co.istad.elearningrestapi.features.course;

import co.istad.elearningrestapi.features.course.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    //Create a new course
    @PostMapping
    void createNew( @Valid @RequestBody CourseCreateRequest courseCreateRequest) {
        courseService.createNew(courseCreateRequest);
    }
    //Find all courses by pagination
    @GetMapping
    Page<CourseResponse> findList(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "25") int size
    ){
        return courseService.findList(page, size);
    }

    //Find a course details
    @GetMapping("/{alias}")
    CourseDetailsResponse findCourseByAlias(@PathVariable String alias) {
        return courseService.findCourseByAlias(alias);
    }

    //Update a course
    @PutMapping("/{alias}")
    CourseResponse updateCourseByAlias(@PathVariable String alias, @Valid @RequestBody CourseUpdateRequest courseUpdateRequest) {
        return courseService.updateCourseByAlias(alias, courseUpdateRequest);
    }

    //Update a course’s thumbnail
    @PutMapping("/{alias}/thumbnail")
    CourseResponse updateCourseThumbnail(@PathVariable String alias, @Valid @RequestBody CourseThumbnailRequest courseThumbnailRequest) {
        return courseService.updateCourseThumbnail(alias, courseThumbnailRequest);
    }

    //Update a course’s categories
    @PutMapping("/{alias}/categories")
    CourseResponse updateCourseCategory(@PathVariable String alias, @Valid @RequestBody CourseCategoryRequest courseCategoryRequest) {
        return courseService.updateCourseCategory(alias, courseCategoryRequest);
    }

    //Disable a course
    @PutMapping("/{alias}/disable")
    void disableCourseByAlias(@PathVariable String alias) {
        courseService.disableCourseByAlias(alias);
    }

}
