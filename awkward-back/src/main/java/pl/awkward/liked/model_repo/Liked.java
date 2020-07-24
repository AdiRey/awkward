package pl.awkward.liked.model_repo;

import lombok.Data;
import pl.awkward.shared.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Liked implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "boolean default true")
    private Byte status;
    @Column(nullable = false)
    private LocalDateTime date;
    @Column(nullable = false)
    private Boolean active;

    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private Long secondUserId;
}