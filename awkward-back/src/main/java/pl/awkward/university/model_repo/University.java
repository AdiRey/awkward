package pl.awkward.university.model_repo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import pl.awkward.address.model_repo.Address;
import pl.awkward.shared.baseStuff.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(uniqueConstraints =
            {@UniqueConstraint(columnNames = {"name", "address_id"})},
        indexes = {@Index(name = "name_address_index", columnList = "name,address_id")})
@Data
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@ToString(callSuper = true, doNotUseGetters = true)
public class University extends BaseEntity {

    @Column(nullable = false, length = 100)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    @JoinColumn(name = "address_id")
    private Address address;

}
