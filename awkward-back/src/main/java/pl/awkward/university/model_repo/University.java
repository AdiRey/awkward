package pl.awkward.university.model_repo;

import lombok.Data;
import pl.awkward.shared.BaseEntity;

import javax.persistence.*;

@Entity
@Data
public class University implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false)
    private Long addressId;
    @Column(columnDefinition = "boolean default true")
    private Boolean active;
}
