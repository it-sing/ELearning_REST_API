package co.istad.elearningrestapi.features.student;

import co.istad.elearningrestapi.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long> {
    @Query("SELECT s FROM Student s JOIN s.user u WHERE u.username = :username")
    Optional<Student> findByUsername(@Param("username") String username);
}
