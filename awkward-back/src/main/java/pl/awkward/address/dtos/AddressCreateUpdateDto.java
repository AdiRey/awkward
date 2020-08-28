package pl.awkward.address.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class AddressCreateUpdateDto {

    @NotBlank(message = "Country cannot be blank.")
    @Size(min = 2, max = 80, message = "Property: country; Min chars: 2; Max chars: 80")
    private String country;

    @NotBlank(message = "City cannot be blank.")
    @Size(min = 2, max = 80, message = "Property: city; Min chars: 2; Max chars: 80")
    private String city;

    @JsonIgnore
    private LocalDateTime addDate;

}
