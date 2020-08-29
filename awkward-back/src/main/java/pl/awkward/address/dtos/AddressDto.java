package pl.awkward.address.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddressDto {

    private Long id;

    private String country;

    private String city;

    private Boolean active;

    private LocalDateTime addDate;

    private LocalDateTime deleteDate;

}
