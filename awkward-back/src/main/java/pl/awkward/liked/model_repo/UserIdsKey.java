package pl.awkward.liked.model_repo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class UserIdsKey implements Serializable {

    @Column(name = "first_user_id")
    private Long firstUserId;

    @Column(name = "second_user_id")
    private Long secondUserId;

}
