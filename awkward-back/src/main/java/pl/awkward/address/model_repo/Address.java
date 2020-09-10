package pl.awkward.address.model_repo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import pl.awkward.shared.baseStuff.BaseEntity;

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
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@ToString(callSuper = true, doNotUseGetters = true)
public class Address extends BaseEntity {

    @Column(name = "country", nullable = false, length = 80)
    private String country;

    @Column(name = "city", nullable = false, length = 80)
    private String city;

}
