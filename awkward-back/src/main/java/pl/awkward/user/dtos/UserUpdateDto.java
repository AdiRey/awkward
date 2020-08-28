package pl.awkward.user.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import pl.awkward.gender.Gender;
import pl.awkward.university.model_repo.University;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * Do update'ów informacji o użytkowniku.
 * */

@Data
public class UserUpdateDto {

    @Email(message = "Email has to be correct.")
    private String email;

    @NotBlank(message = "Username cannot be blank.")
    @Size(min = 5, max = 20, message = "Username should have 5 or more characters but less than or equals to 20.")
    private String username;

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

    @Size(max = 1000, message = "Description cannot be longer than 1000 characters.")
    private String description;

    private Long universityId;

    @NotNull(message = "GenderId cannot be null.")
    private Long genderId;

    @JsonIgnore
    private Gender gender;

    @JsonIgnore
    private Integer age;

    @JsonIgnore
    private University university;
}
