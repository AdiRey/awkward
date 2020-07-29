package pl.awkward.liked.dtos;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LikedCreateDto {
    @NotNull
    private Byte status;
    @NotNull
    private Long secondUserId;
}
