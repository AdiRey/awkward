package pl.awkward.user_address.model_repo;

import lombok.Data;
import pl.awkward.shared.BaseEntity;

import javax.persistence.*;

@Data
@Entity
public class UserAddress implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private Long addressId;
    @Column(columnDefinition = "boolean default true")
    private Boolean active;
}
