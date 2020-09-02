package pl.awkward.liked.model_repo;

import lombok.Data;
import pl.awkward.user.model_repo.User;

import javax.persistence.*;

@Entity(name = "liked")
@Table(indexes = {
        @Index(columnList = "first_user_id,second_user_id"),
        @Index(columnList = "second_user_id,first_user_id")
})
@Data
public class Liked {

    /* ### ID ### */

    @EmbeddedId
    private UserIdsKey id;

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
