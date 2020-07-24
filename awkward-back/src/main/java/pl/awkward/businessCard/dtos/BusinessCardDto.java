package pl.awkward.businessCard.dtos;

import lombok.Data;

@Data
public class BusinessCardDto {
    private Long id;
    private String phoneNumber;
    private String facebookUrl;
    private String instUrl;
    private String snapName;
    private Long userId;
    private Boolean active;
}
