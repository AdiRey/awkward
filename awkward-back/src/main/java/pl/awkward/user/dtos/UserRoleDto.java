package pl.awkward.user.dtos;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserRoleDto {
    @NotNull(message = "RoleId cannot be null.")
    private Long roleId;
}
