package pl.awkward.role.model_repo;

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
public class Role extends BaseEntity {

    @Column(unique = true, nullable = false, length = 20)
    private String name;

    @Column(nullable = false)
    private Integer status;

}
