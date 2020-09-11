package pl.awkward.address.model_repo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import pl.awkward.shared.baseStuff.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
    @NotBlank(message = "Country cannot be blank.")
    @Size(min = 2, max = 80, message = "Property: country; Min chars: 2; Max chars: 80")
    private String country;

    @Column(name = "city", nullable = false, length = 80)
    @NotBlank(message = "City cannot be blank.")
    @Size(min = 2, max = 80, message = "Property: city; Min chars: 2; Max chars: 80")
    private String city;

}
