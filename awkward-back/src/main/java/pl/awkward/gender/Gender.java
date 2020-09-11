package pl.awkward.gender;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import pl.awkward.shared.baseStuff.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Data
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@ToString(callSuper = true, doNotUseGetters = true)
@Entity(name = "gender")
@Table(name = "gender")
public class Gender extends BaseEntity {

    @Column(name = "gender", unique = true, nullable = false, length = 10)
    private String gender;

}
