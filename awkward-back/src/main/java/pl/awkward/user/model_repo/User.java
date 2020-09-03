package pl.awkward.user.model_repo;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import pl.awkward.businessCard.model_repo.BusinessCard;
import pl.awkward.gender.Gender;
import pl.awkward.interest.model_repo.Interest;
import pl.awkward.liked.model_repo.Liked;
import pl.awkward.photo.model_repo.Photo;
import pl.awkward.role.model_repo.Role;
import pl.awkward.shared.BaseEntity;
import pl.awkward.university.model_repo.University;
import pl.awkward.user_address.model_repo.UserAddress;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity(name = "user")
@Table(name = "user")
@Data
public final class User implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true, nullable = false, length = 20)
    private String username;

    @Column(unique = true, nullable = false, length = 150)
    private String email;


    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 70)
    private String surname;


    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = true) // @Column(nullable = false)
    private LocalDateTime addDate;

    private LocalDateTime deleteDate;


    @Column(nullable = false)
    private Integer age;

    @Lob
    private String description;

    @Column(nullable = false)
    private String password;


    @Column(columnDefinition = "boolean default true")
    @Generated(GenerationTime.INSERT)
    private Boolean active;

    @Column(name = "active_now", columnDefinition = "boolean default false")
    @Generated(GenerationTime.INSERT)
    private Boolean activeNow;


    /* ### RELATIONS ### */


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Gender gender;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Role role;


    @ManyToOne(fetch = FetchType.EAGER)
    private University university;


    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Photo> photos;


    @OneToMany(mappedBy = "user",
            fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OrderBy("position asc")
    private List<UserAddress> userAddresses;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_interest",
            joinColumns = {@JoinColumn(name = "id_user", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "id_interest", referencedColumnName = "id")},
            indexes = {@Index(name = "user_interest_index", unique = true, columnList = "id_user,id_interest")})
    @Fetch(FetchMode.SELECT)
    private List<Interest> interests;


    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    private BusinessCard card;


    @OneToMany(mappedBy = "firstUser",
            fetch = FetchType.LAZY)
    private List<Liked> firstLikes;


    @OneToMany(mappedBy = "secondUser",
            fetch = FetchType.LAZY)
    private List<Liked> secondLikes;

}
