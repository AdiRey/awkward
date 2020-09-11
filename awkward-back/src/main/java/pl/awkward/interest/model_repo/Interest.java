package pl.awkward.interest.model_repo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import pl.awkward.shared.baseStuff.BaseEntity;
import pl.awkward.user.model_repo.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@ToString(callSuper = true, doNotUseGetters = true)
@Entity(name = "interest")
@Table(name = "interest",
        indexes = {
            @Index(name = "interest_name_index", columnList = "name", unique = true)
        })
public class Interest extends BaseEntity {

    @Column(nullable = false, length = 50)
    @NotBlank(message = "Interest should not be blank.")
    @Size(min = 2, max = 50, message = "Property: name; Min chars: 2; Max chars: 50")
    private String name;

    @ManyToMany(mappedBy = "interests", fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Null
    private List<User> users = new ArrayList<>();

}
