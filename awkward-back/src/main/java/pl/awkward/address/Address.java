package pl.awkward.address;

import lombok.Data;
import pl.awkward.shared.BaseEntity;

import javax.persistence.*;

@Entity
@Data
public class Address implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 80)
    private String country;
    @Column(nullable = false, length = 80)
    private String city;
    @Column(columnDefinition = "boolean default true")
    private Boolean active;
}
