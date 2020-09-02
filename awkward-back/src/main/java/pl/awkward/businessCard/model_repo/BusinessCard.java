package pl.awkward.businessCard.model_repo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import pl.awkward.shared.BaseEntity;
import pl.awkward.user.model_repo.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "business_card")
@Table(name = "business_card")
@Data
public class BusinessCard implements BaseEntity {

    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "id")
    @MapsId("id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

    @Column(length = 12)
    private String phoneNumber;

    private String facebookUrl;

    private String instUrl;

    @Column(length = 50)
    private String snapName;

    @Column(columnDefinition = "boolean default true")
    @Generated(GenerationTime.INSERT)
    private Boolean active;

    @Column(nullable = false)
    private LocalDateTime addDate;

    private LocalDateTime deleteDate;

}
