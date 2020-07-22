package pl.awkward.pair.dtos;

import lombok.Data;

@Data
public class PairDto {
    private Long id;
    private Long userIdFirst;
    private Long userIdSecond;
    private Boolean active;
}
