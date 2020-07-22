package pl.awkward.user_interest.model_repo;

import lombok.Data;
import pl.awkward.shared.BaseEntity;

import javax.persistence.*;

@Data
@Entity
public class UserInterest implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private Long interestId;
    @Column(columnDefinition = "boolean default true")
    private Boolean active;
}
