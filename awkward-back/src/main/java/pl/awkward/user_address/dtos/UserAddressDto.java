package pl.awkward.user_address.dtos;

import lombok.Data;

@Data
public class UserAddressDto {
    private Long id;
    private Long userId;
    private Long addressId;
    private Boolean active;
}
