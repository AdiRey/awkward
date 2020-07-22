package pl.awkward.user_interest.dtos;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class UserInterestCreateDto {
    @NotNull
    @Min(0)
    private Long userId;
    @NotNull
    @Min(0)
    private Long interestId;
}
