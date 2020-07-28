package pl.awkward.liked.dtos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

@Data
public class LikedCreateDto {
    @NotNull
    private Byte status;
    @NotNull
    private Long secondUserId;
}
