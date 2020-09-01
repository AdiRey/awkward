package pl.awkward.user_address.model_repo;

import lombok.Data;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import pl.awkward.address.model_repo.Address;
import pl.awkward.shared.BaseEntity;
import pl.awkward.user.model_repo.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "position"}))
@Entity
public class UserAddress {

    /* ### ID ### */

    @EmbeddedId
    private EmbeddedIds embeddedIds = new EmbeddedIds();

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @MapsId("addressId")
    @JoinColumn(insertable = false, updatable = false)
    private Address address;

    /* ### OTHER FIELDS ### */

    @Column(nullable = false)
    private Integer position;

    private Integer timeInPercentage;

}
