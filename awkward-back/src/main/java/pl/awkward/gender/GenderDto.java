package pl.awkward.gender;

import lombok.Data;

@Data
public class GenderDto {
    private Long id;
    private String gender;
    private Boolean active;
}
