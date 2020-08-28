package pl.awkward.user.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import pl.awkward.role.model_repo.Role;

import javax.validation.constraints.NotNull;

@Data
public class UserRoleDto {
    @NotNull(message = "RoleId cannot be null.")
    private Long roleId;

    @JsonIgnore
    private Role role;
}
