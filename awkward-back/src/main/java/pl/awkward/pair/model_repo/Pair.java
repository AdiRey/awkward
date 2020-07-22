package pl.awkward.pair.model_repo;

import lombok.Data;
import pl.awkward.shared.BaseEntity;

import javax.persistence.*;

@Data
@Entity
public class Pair implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long userIdFirst;
    @Column(nullable = false)
    private Long userIdSecond;
    @Column(columnDefinition = "boolean default true")
    private Boolean active;
}
