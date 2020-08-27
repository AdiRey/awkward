package pl.awkward.user_address.model_repo;

import lombok.Data;
import pl.awkward.shared.BaseEntity;
import pl.awkward.user.model_repo.User;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class UserAddress implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Column(nullable = false)
//    private Long userId;
    @Column(nullable = false)
    private Long addressId;
    @Column(columnDefinition = "boolean default true")
    private Boolean active;

    @ManyToOne
    private User user;
    private Integer number;
}
