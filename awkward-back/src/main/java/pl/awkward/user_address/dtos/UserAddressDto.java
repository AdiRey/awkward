package pl.awkward.user_address.dtos;

import lombok.Data;
import pl.awkward.address.dtos.AddressShowDto;

@Data
public class UserAddressDto {

    private Long userId;

    private AddressShowDto address;

    private Integer position;

    private Integer timeInPercentage;

}
