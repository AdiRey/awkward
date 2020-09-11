package pl.awkward.address.model_repo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import pl.awkward.shared.baseStuff.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@ToString(callSuper = true, doNotUseGetters = true)
@Entity(name = "address")
@Table(name = "address",
        indexes = {
                @Index(name = "address_country_city_index", columnList = "country,city", unique = true)
        })
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
