package pl.awkward.university.converters;

import org.springframework.stereotype.Service;
import pl.awkward.shared.baseStuff.BaseConverter;
import pl.awkward.university.dtos.UniversityCreateUpdateDto;
import pl.awkward.university.model_repo.University;

import java.util.function.Function;

@Service
public class UniversityCreateUpdateConverter extends BaseConverter<University, UniversityCreateUpdateDto> {

    @Override
    public Function<UniversityCreateUpdateDto, University> toEntity() {
        return dto -> {
            if (dto == null)
                return null;

            University university = new University();

            university.setName(dto.getName());
            university.setAddress(dto.getAddress());

            return university;
        };
    }

    @Override
    public Function<University, UniversityCreateUpdateDto> toDto() {
        return university -> {
            if (university == null)
                return null;

            UniversityCreateUpdateDto dto = new UniversityCreateUpdateDto();

            dto.setName(university.getName());
            dto.setAddress(university.getAddress());

            dto.setAddressId(university.getAddress().getId());

            return dto;
        };
    }
}
