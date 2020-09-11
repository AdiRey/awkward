package pl.awkward.user.dtos;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class UserPasswordDto {

    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%#*?&])[A-Za-z\\d@$!%#*?&]{8,}$",
            message = "Password should have: Minimum eight characters, at least one uppercase letter, one " +
                    "lowercase letter, one number and one special character.")
    private String password;

}
