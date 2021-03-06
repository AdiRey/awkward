package pl.awkward.businessCard.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BusinessCardDto {

    private Long userId;

    private String phoneNumber;

    private String facebookUrl;

    private String instUrl;

    private String snapName;

    private Boolean active;

    private LocalDateTime addDate;

    private LocalDateTime deleteDate;

}
