package pl.awkward.gender;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class GenderDto {

    private Long id;

    private String gender;

    private Boolean active;

    private LocalDateTime addDate;

    private LocalDateTime deleteDate;
}
