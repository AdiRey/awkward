package pl.awkward.liked.dtos;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LikedCreateDto {

    @NotNull
    private Long firstUserId;

    @NotNull
    private Long secondUserId;

    @NotNull
    private Byte firstStatus;

    @NotNull
    private Byte secondStatus;

}
