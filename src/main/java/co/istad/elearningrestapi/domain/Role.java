package co.istad.elearningrestapi.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy ="roles")
    private List<User> users;

    @ManyToMany
    @JoinTable(
            name = "roles_authorities",
            joinColumns = @JoinColumn(name = "roles_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authorities_id",referencedColumnName = "id")
    )
    private List<Authority> authorities;
}
