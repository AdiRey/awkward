package pl.awkward.photo.model_repo;

import lombok.Data;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import pl.awkward.address.model_repo.Address;
import pl.awkward.shared.BaseEntity;
import pl.awkward.user.model_repo.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "position"})})
@Data
public class Photo implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String path;

    @Column(columnDefinition = "boolean default false")
    @Generated(GenerationTime.INSERT)
    private Boolean archive;

    @Column(columnDefinition = "boolean default true")
    @Generated(GenerationTime.INSERT)
    private Boolean active;

    @Column(nullable = false)
    private LocalDateTime addDate;

    private LocalDateTime deleteDate;

    @Column(nullable = false)
    private Integer position;


    /* ### RELATIONS ### */


    @ManyToOne(fetch = FetchType.EAGER)
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

}
