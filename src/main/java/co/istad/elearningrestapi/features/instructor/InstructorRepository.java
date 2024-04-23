package co.istad.elearningrestapi.features.instructor;

import co.istad.elearningrestapi.domain.Instructor;
import co.istad.elearningrestapi.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface InstructorRepository extends JpaRepository<Instructor,Long> {
    @Query("SELECT i FROM Instructor i JOIN i.user u WHERE u.username = :username")
    Optional<Instructor> findByUsername(@Param("username") String username);
}
