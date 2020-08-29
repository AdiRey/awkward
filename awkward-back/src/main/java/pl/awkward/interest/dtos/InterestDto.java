package pl.awkward.interest.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InterestDto {

    private Long id;

    private String name;

    private Boolean active;

    private LocalDateTime addDate;

    private LocalDateTime deleteDate;

}
