package pl.awkward.liked.model_repo;

import lombok.Data;
import pl.awkward.user.model_repo.User;

import javax.persistence.*;

@Entity(name = "liked")
@Table(indexes = {
        @Index(columnList = "firstUserId,secondUserId"),
        @Index(columnList = "secondUserId,firstUserId")
})
@Data
public class Liked {

    /* ### ID ### */

    @EmbeddedId
    private UserIdsKey id = new UserIdsKey();

    @ManyToOne
    @MapsId(value = "firstUserId")
    @JoinColumn(insertable = false, updatable = false)
    private User firstUser;

    @ManyToOne
    @MapsId(value = "secondUserId")
    @JoinColumn(insertable = false, updatable = false)
    private User secondUser;

    /* ### OTHER FIELDS ### */

    private Byte firstStatus;

    private Byte secondStatus;

}
