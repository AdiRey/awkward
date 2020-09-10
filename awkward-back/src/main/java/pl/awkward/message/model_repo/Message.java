package pl.awkward.message.model_repo;

import lombok.Data;
import pl.awkward.pair.model_repo.Pair;
import pl.awkward.user.model_repo.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "topic")
    private Pair pair;

    @Column
    @Lob
    private String message;

    @ManyToOne(optional = false)
    private User user;

    @Lob
    @Column(columnDefinition="BLOB")
    private byte[] attachment;

    @Column(nullable = false)
    private LocalDateTime addTime;

}
