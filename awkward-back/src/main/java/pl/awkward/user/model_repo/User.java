package pl.awkward.user.model_repo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
import pl.awkward.shared.baseStuff.BaseEntity;
import pl.awkward.university.model_repo.University;
import pl.awkward.user_address.model_repo.UserAddress;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity(name = "user")
@Table(name = "user",
        indexes = {
            @Index(name = "user_username_index", columnList = "username"),
            @Index(name = "user_email_index", columnList = "email")
        })
public final class User extends BaseEntity {

    @Column(name = "username", unique = true, nullable = false, length = 20)
    private String username;

    @Column(name = "email", unique = true, nullable = false, length = 150)
    private String email;


    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "surname", nullable = false, length = 70)
    private String surname;


    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;


    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "description")
    @Lob
    private String description;

    @Column(name = "password", nullable = false)
    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%#*?&])[A-Za-z\\d@$!%#*?&]{8,}$",
            message = "Password should have: Minimum eight characters, at least one uppercase letter, one " +
                    "lowercase letter, one number and one special character.")
    private String password;


    @Column(name = "active", columnDefinition = "boolean default true")
    @Generated(GenerationTime.INSERT)
    private Boolean active;

    @Column(name = "active_now", columnDefinition = "boolean default false")
    @Generated(GenerationTime.INSERT)
    private Boolean activeNow;


    /* ### RELATIONS ### */


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "gender_id", insertable = false, updatable = false)
    private Gender gender;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private Role role;


    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    private University university;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Photo> photos;


    @OneToMany(mappedBy = "user",
            fetch = FetchType.EAGER, cascade = CascadeType.ALL,
            orphanRemoval = true)
    @OrderBy("position asc")
    private List<UserAddress> userAddresses;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_interest",
            joinColumns = {@JoinColumn(name = "id_user", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "id_interest", referencedColumnName = "id")},
            indexes = {@Index(name = "user_interest_index", columnList = "id_user,id_interest", unique = true)})
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


    public User(Long id) {
        super.setId(id);
    }

}
