package pl.awkward.user_address.dtos;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class UserAddressCreateDto {

    @NotNull
    private Long userId;

    @NotNull
    private Long addressId;

    @NotNull
    @Min(1)
    private Integer position;

    @NotNull
    @Max(1)
    @Min(0)
    private Integer timeInPercentage;

}
