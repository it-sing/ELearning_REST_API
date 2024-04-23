package co.istad.elearningrestapi.mapper;

import co.istad.elearningrestapi.domain.Course;
import co.istad.elearningrestapi.features.course.dto.CourseDetailsResponse;
import co.istad.elearningrestapi.features.course.dto.CourseResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseResponse toCourseResponse(Course course);

    CourseDetailsResponse toCourseDetailResponse(Course course);
}
