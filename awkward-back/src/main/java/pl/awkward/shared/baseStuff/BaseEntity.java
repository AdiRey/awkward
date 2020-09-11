package pl.awkward.shared.baseStuff;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Null(message = "Id must be null.")
    private Long id;

    @Column(name = "active", columnDefinition = "boolean default true")
    @Generated(GenerationTime.INSERT)
    @Null(message = "Active must be null.")
    private Boolean active;

    @Column(name = "add_date", nullable = false)
    @Null(message = "AddDate must be null.")
    private LocalDateTime addDate;

    @Column(name = "delete_date")
    @Null(message = "DeleteDate must be null.")
    private LocalDateTime deleteDate;

}
