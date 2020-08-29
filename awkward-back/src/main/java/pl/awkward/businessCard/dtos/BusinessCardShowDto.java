package pl.awkward.businessCard.dtos;

import lombok.Data;

@Data
public class BusinessCardShowDto {

    private Long userId;

    private String phoneNumber;

    private String facebookUrl;

    private String instUrl;

    private String snapName;

}
