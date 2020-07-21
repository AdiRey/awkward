package pl.awkward.user.model_repo;

import lombok.Data;
import pl.awkward.shared.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public final class User implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, length = 150)
    private String email;
    @Column(unique = true, nullable = false, length = 20)
    private String login;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 70)
    private String surname;
    @Column(nullable = false)
    private LocalDate dateOfBirth;
    private Integer age;
    @Column(columnDefinition = "varchar(255) default 'Brak opisu.'")
    private String description;
    @Column(nullable = false)
    private String password;
    private Boolean active;

    @Column(nullable = false)
    private Long genderId;
    @Column(nullable = false)
    private Long roleId;
    private Long universityId;
}
