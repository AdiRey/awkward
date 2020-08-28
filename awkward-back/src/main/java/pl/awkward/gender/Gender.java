package pl.awkward.gender;

import lombok.Data;
import pl.awkward.shared.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Gender implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, length = 10)
    private String gender;
    @Column(columnDefinition = "boolean default true")
    private Boolean active;
    @Column(nullable = true) // @Column(nullable = false)
    private LocalDateTime addDate;
}
