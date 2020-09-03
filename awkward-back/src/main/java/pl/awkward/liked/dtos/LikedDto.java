package pl.awkward.liked.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import pl.awkward.user.model_repo.User;

@Data
public class LikedDto {

    private Long firstUserId;

    private Long secondUserId;

    private Byte firstStatus;

    private Byte secondStatus;

    @JsonIgnore
    private User firstUser;

    @JsonIgnore
    private User secondUser;

}
