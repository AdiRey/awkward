package pl.awkward.user_address.model_repo;

import lombok.Data;
import pl.awkward.address.model_repo.Address;
import pl.awkward.user.model_repo.User;

import javax.persistence.*;

@Data
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "position"}))
@Entity
public class UserAddress {

    /* ### ID ### */

    @EmbeddedId
    private EmbeddedIds embeddedIds;

    @ManyToOne(optional = false)
    @MapsId("userId")
    @JoinColumn(insertable = false, updatable = false)
    private User user;

    @ManyToOne(optional = false)
    @MapsId("addressId")
    @JoinColumn(insertable = false, updatable = false)
    private Address address;

    /* ### OTHER FIELDS ### */

    @Column(nullable = false)
    private Integer position;

    private Integer timeInPercentage;

}
