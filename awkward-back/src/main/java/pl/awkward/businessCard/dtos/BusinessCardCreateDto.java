package pl.awkward.businessCard.dtos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class BusinessCardCreateDto {

    @Size(min = 12, max = 12)
    private String phoneNumber;

    private String facebookUrl;

    private String instUrl;

    @Size(max = 50)
    private String snapName;

    @NotNull
    private Long userId;

}
