package pl.awkward.message.model_repo;

import lombok.Data;
import pl.awkward.shared.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Message implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Lob
    private String message;
    @Column(nullable = false)
    private Long pairId;
    @Column(nullable = false)
    private Long fromUserId;
    @Column(nullable = false)
    private LocalDateTime addTime;
    @Column(nullable = false)
    private Boolean active = true;
    @Column(nullable = false)
    private Boolean activeReceiver = true;

    @Column(nullable = true) // @Column(nullable = false)
    private LocalDateTime addDate;
}
