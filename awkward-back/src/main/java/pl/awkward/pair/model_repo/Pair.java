package pl.awkward.pair.model_repo;

import lombok.Data;
import pl.awkward.liked.model_repo.Liked;
import pl.awkward.liked.model_repo.UserIdsKey;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Pair {

    @Id
    private String topic;

    @OneToOne
    @JoinColumn(name = "first_user_id", referencedColumnName = "first_user_id")
    @JoinColumn(name = "second_user_id", referencedColumnName = "second_user_id")
    private Liked liked;

    @Column(nullable = false)
    private LocalDateTime addDate;

    @Column(nullable = false)
    private Byte status;

}
