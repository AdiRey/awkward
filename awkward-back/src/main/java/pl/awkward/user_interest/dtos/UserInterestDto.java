package pl.awkward.user_interest.dtos;

import lombok.Data;

@Data
public class UserInterestDto {
    private Long id;
    private Long userId;
    private Long interestId;
    private Boolean active;
}
