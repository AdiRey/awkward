package pl.awkward.user.dtos;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

/**
 * Do update'ów informacji o użytkowniku.
 * */

@Data
public class UserUpdateDto {
    @Email(message = "Email has to be correct.")
    private String email;
    @NotBlank(message = "Login cannot be blank.")
    @Size(min = 5, max = 20, message = "Login should have 5 or more characters but less than or equals to 20.")
    private String login;
    @NotBlank(message = "Name should not be blank.")
    @Size(max = 50, message = "Name cannot be longer than 50 characters.")
    @Size(min = 2, message = "Name cannot be shorter than 2 characters.")
    private String name;
    @NotBlank(message = "Surname should not be blank.")
    @Size(max = 70, message = "Surname cannot be longer than 70 characters.")
    @Size(min = 2, message = "Surname cannot be shorter than 2 characters.")
    private String surname;
    @NotNull(message = "Date of birth cannot be null.")
    private LocalDate dateOfBirth;
    @Size(max = 255, message = "Description cannot be longer than 255 characters.")
    private String description;
    private Long universityId;
}
