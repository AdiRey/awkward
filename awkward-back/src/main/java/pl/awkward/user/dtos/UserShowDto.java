package pl.awkward.user.dtos;

import lombok.Data;

@Data
public class UserShowDto {
    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private String description;

    private Long genderId;
    private Long roleId;
    private Long universityId;
}
