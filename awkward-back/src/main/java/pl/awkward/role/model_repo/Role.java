package pl.awkward.role.model_repo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import pl.awkward.shared.baseStuff.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Data
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@ToString(callSuper = true, doNotUseGetters = true)
public class Role extends BaseEntity {

    @Column(unique = true, nullable = false, length = 20)
    @NotBlank
    @Size(min = 2, max = 20, message = "Property: name; Min chars: 2; Max chars: 20")
    private String name;

    @Column(nullable = false)
    @NotNull
    @Min(value = 0, message = "Property: status; Min value: 0")
    private Integer status;

}
