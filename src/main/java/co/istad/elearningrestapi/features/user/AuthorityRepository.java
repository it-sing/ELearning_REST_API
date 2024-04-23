package co.istad.elearningrestapi.features.user;

import co.istad.elearningrestapi.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Authority findByName(String name);
}
