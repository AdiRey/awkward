package pl.awkward.gender;

import org.springframework.stereotype.Service;
import pl.awkward.shared.BaseConverter;

import java.util.function.Function;

@Service
public class GenderConverter extends BaseConverter<Gender, GenderDto> {
    @Override
    public Function<GenderDto, Gender> toEntity() {
        return dto -> {
            Gender gender = new Gender();
            gender.setId(dto.getId());
            gender.setGender(dto.getGender());
            gender.setActive(dto.getActive());
            return gender;
        };
    }

    @Override
    public Function<Gender, GenderDto> toDto() {
        return gender -> {
            GenderDto dto = new GenderDto();
            dto.setId(gender.getId());
            dto.setGender(gender.getGender());
            dto.setActive(gender.getActive());
            return dto;
        };
    }
}
