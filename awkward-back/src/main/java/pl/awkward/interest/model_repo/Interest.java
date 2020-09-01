package pl.awkward.interest.model_repo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
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
    @Generated(GenerationTime.INSERT)
    private Boolean active;

    @Column(nullable = false)
    private LocalDateTime addDate;

    private LocalDateTime deleteDate;

    @ManyToMany(mappedBy = "interests", fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<User> users;
}
