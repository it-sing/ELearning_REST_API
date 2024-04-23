package co.istad.elearningrestapi.init;

import co.istad.elearningrestapi.domain.Authority;
import co.istad.elearningrestapi.domain.Role;
import co.istad.elearningrestapi.features.user.AuthorityRepository;
import co.istad.elearningrestapi.features.user.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final RoleRepository roleRepository;
    private final AuthorityRepository authorityRepository;

    @PostConstruct
    void initData() {

        if (roleRepository.count() < 1 && authorityRepository.count() < 1) {


            List<Authority> userAuthorities = createAuthorities("write, read, update, delete, progressRead, elearningRead".split(","));
            List<Authority> studentAuthorities = createAuthorities("ProgressWrite".split(","));
            List<Authority> instructorAuthorities = createAuthorities("elearningWrite, elearningUpdate, elearningDelete".split(","));
            List<Authority> adminAuthorities = createAuthorities("progressWrite, elearningWrite, elearning Update, elearningDelete".split(","));

            authorityRepository.saveAll(userAuthorities);
            authorityRepository.saveAll(studentAuthorities);
            authorityRepository.saveAll(instructorAuthorities);
            authorityRepository.saveAll(adminAuthorities);

            Role userRole = new Role();
            userRole.setName("USER");
            Role studentRole = new Role();
            studentRole.setName("STUDENT");
            Role instructorRole = new Role();
            instructorRole.setName("INSTRUCTOR");
            Role adminRole = new Role();
            adminRole.setName("ADMIN");

            userRole.setAuthorities(userAuthorities);
            studentRole.setAuthorities(studentAuthorities);
            instructorRole.setAuthorities(instructorAuthorities);
            adminRole.setAuthorities(adminAuthorities);

            roleRepository.saveAll(List.of(userRole, studentRole, instructorRole, adminRole));
        }
    }
    private List<Authority> createAuthorities(String[] authorityNames) {
        List<Authority> authorities = new ArrayList<>();
        for (String authorityName : authorityNames) {
            Authority authority = new Authority();
            authority.setName(authorityName.trim());
            authorities.add(authority);
        }
        return authorities;
    }

}
