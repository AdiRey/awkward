package pl.awkward.gender;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import pl.awkward.shared.baseStuff.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@ToString(callSuper = true, doNotUseGetters = true)
public class Gender extends BaseEntity {

    @Column(name = "gender", unique = true, nullable = false, length = 10)
    private String gender;

}
