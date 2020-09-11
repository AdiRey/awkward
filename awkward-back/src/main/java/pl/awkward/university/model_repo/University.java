package pl.awkward.university.model_repo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import pl.awkward.address.model_repo.Address;
import pl.awkward.shared.baseStuff.BaseEntity;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@ToString(callSuper = true, doNotUseGetters = true)
@Entity(name = "university")
@Table(name = "university",
        indexes = {
                @Index(name = "university_name_address_id_index", columnList = "name,address_id", unique = true)
        })
public class University extends BaseEntity {

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    @JoinColumn(name = "address_id", updatable = false)
    private Address address;

}
