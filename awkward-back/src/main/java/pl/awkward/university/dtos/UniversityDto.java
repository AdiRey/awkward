package pl.awkward.university.dtos;

import lombok.Data;
import pl.awkward.address.dtos.AddressDto;

import java.time.LocalDateTime;

@Data
public class UniversityDto {

    private Long id;

    private String name;

    private AddressDto address;

    private Boolean active;

    private LocalDateTime addDate;

    private LocalDateTime deleteDate;

}
