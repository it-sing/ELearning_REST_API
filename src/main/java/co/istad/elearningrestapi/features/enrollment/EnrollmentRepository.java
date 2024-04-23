package co.istad.elearningrestapi.features.enrollment;

import co.istad.elearningrestapi.domain.Course;
import co.istad.elearningrestapi.domain.Enrollment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment,Long> {
    List<Enrollment> findAll(Specification<Enrollment> spec, Pageable pageable);

    Optional<Enrollment> findByCode(String code);

}
