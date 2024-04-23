package co.istad.elearningrestapi.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name="students")
public class Student {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String highSchool;

    private Boolean isBlocked;

    private String university;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "student")
    private List<Enrollment> enrollments;
}