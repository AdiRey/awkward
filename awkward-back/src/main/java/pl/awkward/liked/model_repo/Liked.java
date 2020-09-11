package pl.awkward.liked.model_repo;

import lombok.Data;
import pl.awkward.user.model_repo.User;

import javax.persistence.*;

@Data
@Entity(name = "liked")
@Table(indexes = {
        @Index(name = "liked_firstId_secondId_index", columnList = "first_user_id,second_user_id"),
        @Index(name = "liked_secondId_firstId_index", columnList = "second_user_id,first_user_id")
})
public class Liked {

    /* ### ID ### */

    @EmbeddedId
    private UserIdsKey id = new UserIdsKey();

    @ManyToOne(optional = false)
    @MapsId(value = "firstUserId")
    @JoinColumn(insertable = false, updatable = false)
    private User firstUser;

    @ManyToOne(optional = false)
    @MapsId(value = "secondUserId")
    @JoinColumn(insertable = false, updatable = false)
    private User secondUser;

    /* ### OTHER FIELDS ### */

    private Byte firstStatus;

    private Byte secondStatus;

}
