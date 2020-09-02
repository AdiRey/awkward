package pl.awkward.user.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import pl.awkward.gender.Gender;
import pl.awkward.role.model_repo.Role;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserCreateDto {

    @NotBlank(message = "Login cannot be blank.")
    @Size(min = 5, max = 20, message = "Login should have 5 or more characters but less than or equals to 20.")
    private String username;

    @Email(message = "Email has to be correct.")
    private String email;

    @NotBlank(message = "Name should not be blank.")
    @Size(max = 50, message = "Name cannot be longer than 50 characters.")
    @Size(min = 2, message = "Name cannot be shorter than 2 characters.")
    private String name;

    @NotBlank(message = "Surname should not be blank.")
    @Size(max = 70, message = "Surname cannot be longer than 70 characters.")
    @Size(min = 2, message = "Surname cannot be shorter than 2 characters.")
    private String surname;

    @NotNull(message = "DateOfBirth cannot be null.")
    private LocalDate dateOfBirth;

    @Size(max = 1000, message = "Description cannot be longer than 1000 characters.")
    private String description;

    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%#*?&])[A-Za-z\\d@$!%#*?&]{8,}$",
            message = "Password should have: Minimum eight characters, at least one uppercase letter, one " +
                    "lowercase letter, one number and one special character.")
    private String password;

    @NotNull(message = "GenderId cannot be null.")
    private Long genderId;

    @JsonIgnore
    private Gender gender;

    @JsonIgnore
    private Role role;

    @JsonIgnore
    private Integer age;

}
