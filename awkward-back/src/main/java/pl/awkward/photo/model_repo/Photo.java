package pl.awkward.photo.model_repo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import pl.awkward.address.model_repo.Address;
import pl.awkward.shared.baseStuff.BaseEntity;
import pl.awkward.user.model_repo.User;

import javax.persistence.*;


@Data
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@ToString(callSuper = true, doNotUseGetters = true)
@Entity(name = "photo")
@Table(
        name = "photo",
        indexes = {
                @Index(name = "user_index", columnList = "user_id,address_id")
        })
public class Photo extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String path;

    @Column(columnDefinition = "boolean default false")
    @Generated(GenerationTime.INSERT)
    private Boolean archive;


    /* ### RELATIONS ### */


    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

}
