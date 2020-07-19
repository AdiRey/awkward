package pl.awkward.university.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UniversityCreateUpdateDto {
    @NotBlank
    @Size(min = 2, max = 100)
    private String name;
    @NotNull
    private Long addressId;
}
