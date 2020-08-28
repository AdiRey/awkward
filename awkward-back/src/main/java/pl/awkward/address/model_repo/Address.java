package pl.awkward.address.model_repo;

import lombok.Data;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import pl.awkward.shared.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "address")
@Table(name = "address",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"country", "city"})
        },
        indexes = {
            @Index(columnList = "country,city")
        })
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
    @Generated(GenerationTime.INSERT)
    private Boolean active;

    @Column(nullable = false)
    private LocalDateTime addDate;

    private LocalDateTime deleteDate;

}
