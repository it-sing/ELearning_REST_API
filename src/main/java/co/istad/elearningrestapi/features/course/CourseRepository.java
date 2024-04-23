package co.istad.elearningrestapi.features.course;

import co.istad.elearningrestapi.domain.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course,Long> {
    Optional<Course> findByAlias(String alias);

    @Modifying
    @Query("""
    UPDATE Course AS c
    SET c.isDeleted = TRUE
    WHERE c.alias = ?1
    """)
    void disableCourseByAlias(String alias);

    Boolean existsByAlias(String alias);
    Page<Course> findByIsDeletedFalse(Pageable pageable);
    Optional<Course> findByAliasAndIsDeletedFalse(String alias);
}
