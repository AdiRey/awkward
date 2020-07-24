package pl.awkward.pair.dtos;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PairCreateDto {
    @NotNull
    private Long userIdFirst;
    @NotNull
    private Long userIdSecond;
}
