package pl.awkward.liked.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import pl.awkward.user.model_repo.User;

import javax.validation.constraints.NotNull;

@Data
public class LikedCreateDto {

    @NotNull
    private Long firstUserId;

    @NotNull
    private Long secondUserId;

    @NotNull
    private Byte status;

    @JsonIgnore
    private Byte secondStatus;

    @JsonIgnore
    private User firstUser;

    @JsonIgnore
    private User secondUser;

}
