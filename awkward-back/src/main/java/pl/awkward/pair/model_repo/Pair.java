package pl.awkward.pair.model_repo;

import lombok.Data;
import pl.awkward.liked.model_repo.Liked;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity(name = "pair")
@Table(name = "pair")
public class Pair {

    @Id
    @Column(length = 32)
    private String topic;

    @OneToOne
    @JoinColumn(name = "first_user_id", referencedColumnName = "first_user_id")
    @JoinColumn(name = "second_user_id", referencedColumnName = "second_user_id")
    @NotNull
    private Liked liked;

    @Column(nullable = false)
    private LocalDateTime addDate;

    @Column(nullable = false)
    @NotNull
    @Min(1)
    private Byte status;

}
