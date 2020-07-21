package pl.awkward.role.dtos;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RoleCreateUpdateDto {
    @Size(min = 2, max = 20, message = "Property: name; Min chars: 2; Max chars: 20")
    private String name;
    @NotNull
    @Min(value = 0, message = "Property: status; Min value: 0")
    private Integer status;
}
