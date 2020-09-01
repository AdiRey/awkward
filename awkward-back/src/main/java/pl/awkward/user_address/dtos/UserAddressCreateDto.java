package pl.awkward.user_address.dtos;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserAddressCreateDto {

    @NotNull
    private Long userId;

    @NotNull
    private Long addressId;

}
