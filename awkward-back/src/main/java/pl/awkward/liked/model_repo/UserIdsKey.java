package pl.awkward.liked.model_repo;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class UserIdsKey implements Serializable {

    private Long firstUserId;

    private Long secondUserId;

}
