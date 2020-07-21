package pl.awkward.university.converters;

import org.springframework.stereotype.Service;
import pl.awkward.shared.BaseConverter;
import pl.awkward.university.dtos.UniversityDto;
import pl.awkward.university.model_repo.University;

import java.util.function.Function;

@Service
public class UniversityConverter extends BaseConverter<University, UniversityDto> {
    @Override
    public Function<UniversityDto, University> toEntity() {
        return dto -> {
            University university = new University();
            convertIfNotNull(university::setId, dto::getId);
            convertIfNotNull(university::setName, dto::getName);
            convertIfNotNull(university::setAddressId, dto::getAddressId);
            convertIfNotNull(university::setActive, dto::getActive);
            return university;
        };
    }

    @Override
    public Function<University, UniversityDto> toDto() {
        return university -> {
            UniversityDto dto = new UniversityDto();
            convertIfNotNull(dto::setId, university::getId);
            convertIfNotNull(dto::setName, university::getName);
            convertIfNotNull(dto::setAddressId, university::getAddressId);
            convertIfNotNull(dto::setActive, university::getActive);
            return dto;
        };
    }
}
