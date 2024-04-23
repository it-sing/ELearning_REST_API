package co.istad.elearningrestapi.features.course;

import co.istad.elearningrestapi.features.course.dto.*;
import org.springframework.data.domain.Page;

public interface CourseService {

    void createNew(CourseCreateRequest courseCreateRequest);
    Page<CourseResponse> findList(int page, int size);
    CourseDetailsResponse findCourseByAlias(String alias);
    CourseResponse updateCourseByAlias(String alias, CourseUpdateRequest courseUpdateRequest);
    CourseResponse updateCourseThumbnail(String alias, CourseThumbnailRequest courseThumbnailRequest);
    CourseResponse updateCourseCategory(String alias, CourseCategoryRequest courseCategoryRequest);
    void disableCourseByAlias(String alias);
}
