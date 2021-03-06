package pl.awkward.gender;

import org.springframework.stereotype.Service;
import pl.awkward.shared.baseStuff.BaseConverter;

import java.util.function.Function;

@Service
public class GenderConverter extends BaseConverter<Gender, GenderDto> {
    @Override
    public Function<GenderDto, Gender> toEntity() {
        return dto -> {
            if (dto == null)
                return null;

            Gender gender = new Gender();

            gender.setId(dto.getId());
            gender.setGender(dto.getGender());
            gender.setActive(dto.getActive());
            gender.setAddDate(dto.getAddDate());
            gender.setDeleteDate(dto.getDeleteDate());

            return gender;
        };
    }

    @Override
    public Function<Gender, GenderDto> toDto() {
        return gender -> {
            if (gender == null)
                return null;

            GenderDto dto = new GenderDto();

            dto.setId(gender.getId());
            dto.setGender(gender.getGender());
            dto.setActive(gender.getActive());
            dto.setAddDate(gender.getAddDate());
            dto.setDeleteDate(gender.getDeleteDate());

            return dto;
        };
    }
}
