package co.istad.elearningrestapi.features.user;

import co.istad.elearningrestapi.domain.Authority;
import co.istad.elearningrestapi.domain.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    //  Sort by id and filter users by username, email, nationalIdCard, phoneNumber, name,
    @Query("SELECT DISTINCT u FROM User u " +
            "JOIN FETCH u.roles r " +
            "WHERE (:username IS NULL OR u.username = :username) " +
            "AND (:email IS NULL OR u.email = :email) " +
            "AND (:nationalIdCard IS NULL OR u.nationalIdCard = :nationalIdCard) " +
            "AND (:phoneNumber IS NULL OR u.phoneNumber = :phoneNumber) " +
            "AND (:name IS NULL OR u.givenName = :name) " +
            "AND (:gender IS NULL OR u.gender = :gender) " +
            "AND (:role IS NULL OR :role IN (SELECT r.name FROM u.roles r)) " +
            "AND (:isDelete IS NULL OR u.isDeleted = :isDelete)")
    List<User> findUsers(Sort sort,
                         @Param("username") String username,
                         @Param("email") String email,
                         @Param("nationalIdCard") String nationalIdCard,
                         @Param("phoneNumber") String phoneNumber,
                         @Param("name") String name,
                         @Param("gender") String gender,
                         @Param("role") String role,
                         @Param("isDelete") boolean isDelete);

    // Find user by username
    @Query("SELECT u FROM User u WHERE u.username = :username")
    List<User> findByUsername(@Param("username") String username);
//    Optional<User> findByUsername(String username);

}
