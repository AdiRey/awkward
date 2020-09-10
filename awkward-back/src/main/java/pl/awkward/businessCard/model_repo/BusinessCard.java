package pl.awkward.businessCard.model_repo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import pl.awkward.shared.baseStuff.BaseEntity;
import pl.awkward.user.model_repo.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "business_card")
@Table(name = "business_card")
@Data
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@ToString(callSuper = true, doNotUseGetters = true)
public class BusinessCard extends BaseEntity {

    @Id
    @AttributeOverride(name = "id", column = @Column(name = "id"))
    private Long id;

    @OneToOne(optional = false, mappedBy = "card")
    @JoinColumn(name = "id")
    @MapsId("id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Transient
    private User user;

    @Column(name = "phone_number", length = 12)
    private String phoneNumber;

    @Column(name = "facebook_url")
    private String facebookUrl;

    @Column(name = "inst_url")
    private String instUrl;

    @Column(name = "snap_name", length = 50)
    private String snapName;

}
