package pl.awkward.interest.model_repo;

import lombok.Data;
import pl.awkward.shared.BaseEntity;
import pl.awkward.user.model_repo.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Interest implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, length = 50)
    private String name;
    @Column(columnDefinition = "boolean default true")
    private Boolean active;

    @ManyToMany
    private List<User> users;
    @Column(nullable = true) // @Column(nullable = false)
    private LocalDateTime addDate;
    private LocalDateTime deleteDate;
}
