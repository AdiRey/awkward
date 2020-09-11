package pl.awkward.user.dtos;

import lombok.Data;
import pl.awkward.role.model_repo.Role;

import javax.validation.constraints.NotNull;

@Data
public class UserRoleDto {

    @NotNull(message = "Role cannot be null.")
    private Role role;

}
