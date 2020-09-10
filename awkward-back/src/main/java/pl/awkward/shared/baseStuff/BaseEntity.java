package pl.awkward.shared.baseStuff;

import lombok.*;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "active", columnDefinition = "boolean default true")
    @Generated(GenerationTime.INSERT)
    private Boolean active;

    @Column(name = "add_date", nullable = false)
    private LocalDateTime addDate;

    @Column(name = "delete_date")
    private LocalDateTime deleteDate;

}
