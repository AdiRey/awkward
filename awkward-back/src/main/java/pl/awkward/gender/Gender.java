package pl.awkward.gender;

import lombok.Data;
import pl.awkward.shared.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Gender implements BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true, nullable = false, length = 10)
    private String gender;
    @Column(columnDefinition = "boolean default true")
    private Boolean active;
}
