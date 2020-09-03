package pl.awkward.pair.dtos;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class PairCreateDto {

    @NotNull
    @Min(1)
    private Long firstUserId;

    @NotNull
    @Min(1)
    private Long secondUserId;

    @NotNull
    @Min(1)
    private Byte status;

}
