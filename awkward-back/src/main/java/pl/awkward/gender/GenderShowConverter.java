package pl.awkward.gender;

import org.springframework.stereotype.Service;
import pl.awkward.shared.baseStuff.BaseConverter;

import java.util.function.Function;

@Service
public class GenderShowConverter extends BaseConverter<Gender, GenderShowDto> {

    @Override
    public Function<GenderShowDto, Gender> toEntity() {
        return dto -> {
            if (dto == null)
                return null;

            Gender gender = new Gender();

            gender.setId(dto.getId());
            gender.setGender(dto.getGender());

            return gender;
        };
    }

    @Override
    public Function<Gender, GenderShowDto> toDto() {
        return gender -> {
            if (gender == null)
                return null;

            GenderShowDto dto = new GenderShowDto();

            dto.setId(gender.getId());
            dto.setGender(gender.getGender());

            return dto;
        };
    }
}
