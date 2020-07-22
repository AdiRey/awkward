package pl.awkward.interest.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class InterestCreateUpdateDto {
    @NotBlank(message = "Interest should not be blank.")
    @Size(min = 2, max = 50, message = "Property: name; Min chars: 2; Max chars: 50")
    private String name;
}
