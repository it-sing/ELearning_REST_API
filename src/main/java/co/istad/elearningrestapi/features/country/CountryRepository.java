package co.istad.elearningrestapi.features.country;

import co.istad.elearningrestapi.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository <Country , String> {
    Optional<Country> findByIso(String iso);
}
