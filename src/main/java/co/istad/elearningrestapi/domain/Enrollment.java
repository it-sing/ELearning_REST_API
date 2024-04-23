package co.istad.elearningrestapi.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Entity
@NoArgsConstructor
@Table(name="enrollments")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String code;

    private LocalTime enrolledAt;

    private boolean isDeleted;

    private boolean isCertified;

    private int progress;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Course course;

}
