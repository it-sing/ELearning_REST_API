package co.istad.elearningrestapi.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String flag;

    @Column(nullable = false, length = 10)
    private String iso;

    @Column(nullable = false, length = 60)
    private String name;

    private String nicename;

    @Column(nullable = false)
    private int numberCode;

    @Column(nullable = false)
    private String phoneCode;

    @OneToMany(mappedBy = "country")
    private List<City> cities;

    @OneToMany(mappedBy = "country")
    private List<User> users;
}
