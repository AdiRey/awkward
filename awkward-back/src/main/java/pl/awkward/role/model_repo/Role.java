package pl.awkward.role.model_repo;

import lombok.Data;
import pl.awkward.shared.BaseEntity;

import javax.persistence.*;

@Entity
@Data
public class Role implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, length = 20)
    private String name;
    @Column(nullable = false)
    private Integer status;
    @Column(columnDefinition = "boolean default true")
    private Boolean active;
}
