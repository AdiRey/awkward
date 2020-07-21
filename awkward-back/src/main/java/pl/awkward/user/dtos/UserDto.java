package pl.awkward.user.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String login;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private Integer age;
    private String description;
    private Boolean active;

    private Long genderId;
    private Long roleId;
    private Long universityId;
}
