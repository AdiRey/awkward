package pl.awkward.gender;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GenderDto {

    private Long id;

    private String gender;

    private Boolean active;

    private LocalDateTime addDate;

    private LocalDateTime deleteDate;
}
