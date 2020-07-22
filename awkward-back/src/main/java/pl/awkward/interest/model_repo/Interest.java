package pl.awkward.interest.model_repo;

import lombok.Data;
import pl.awkward.shared.BaseEntity;

import javax.persistence.*;

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
}
