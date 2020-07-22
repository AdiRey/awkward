package pl.awkward.user_address.dtos;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class UserAddressCreateDto {
    @NotNull
    @Min(0)
    private Long userId;
    @NotNull
    @Min(0)
    private Long addressId;
}
