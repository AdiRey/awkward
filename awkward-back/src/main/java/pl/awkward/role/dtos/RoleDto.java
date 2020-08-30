package pl.awkward.role.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RoleDto {

    private Long id;

    private String name;

    private Integer status;

    private Boolean active;

    private LocalDateTime addDate;

    private LocalDateTime deleteDate;

}
