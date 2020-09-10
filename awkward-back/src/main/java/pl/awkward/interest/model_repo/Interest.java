package pl.awkward.interest.model_repo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import pl.awkward.shared.baseStuff.BaseEntity;
import pl.awkward.user.model_repo.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@ToString(callSuper = true, doNotUseGetters = true)
public class Interest extends BaseEntity {

    @Column(unique = true, nullable = false, length = 50)
    private String name;

    @ManyToMany(mappedBy = "interests", fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<User> users = new ArrayList<>();

}
