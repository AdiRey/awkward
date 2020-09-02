package pl.awkward.photo.dtos;

import lombok.Data;
import pl.awkward.address.dtos.AddressShowDto;

import java.time.LocalDateTime;

@Data
public class PhotoShowDto {

    private Long id;

    private String path;

    private Boolean archive;

    private Boolean active;

    private LocalDateTime addDate;

    private LocalDateTime deleteDate;

    private AddressShowDto address;

}
