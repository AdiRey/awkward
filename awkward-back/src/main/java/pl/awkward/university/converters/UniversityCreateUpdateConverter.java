package pl.awkward.university.converters;

import org.springframework.stereotype.Service;
import pl.awkward.shared.BaseConverter;
import pl.awkward.university.dtos.UniversityCreateUpdateDto;
import pl.awkward.university.model_repo.University;

import java.util.function.Function;

@Service
public class UniversityCreateUpdateConverter extends BaseConverter<University, UniversityCreateUpdateDto> {
    @Override
    public Function<UniversityCreateUpdateDto, University> toEntity() {
        return dto -> {
            University university = new University();
            convertIfNotNull(university::setName, dto::getName);
            convertIfNotNull(university::setAddressId, dto::getAddressId);
            return university;
        };
    }

    @Override
    public Function<University, UniversityCreateUpdateDto> toDto() {
        return university -> {
            UniversityCreateUpdateDto dto = new UniversityCreateUpdateDto();
            convertIfNotNull(dto::setName, university::getName);
            convertIfNotNull(dto::setAddressId, university::getAddressId);
            return dto;
        };
    }
}
