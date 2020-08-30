package pl.awkward.university.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.awkward.address.dtos.AddressShowDto;
import pl.awkward.address.model_repo.Address;
import pl.awkward.shared.BaseConverter;
import pl.awkward.university.dtos.UniversityShowDto;
import pl.awkward.university.model_repo.University;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class UniversityShowConverter extends BaseConverter<University, UniversityShowDto> {

    private final BaseConverter<Address, AddressShowDto> addressShowConverter;

    @Override
    public Function<UniversityShowDto, University> toEntity() {
        return dto -> {
            if (dto == null)
                return null;

            University university = new University();

            university.setId(dto.getId());
            university.setName(dto.getName());
            university.setAddress(this.addressShowConverter.toEntity().apply(dto.getAddress()));

            return university;
        };
    }

    @Override
    public Function<University, UniversityShowDto> toDto() {
        return university -> {
            if (university == null)
                return null;

            UniversityShowDto dto = new UniversityShowDto();

            dto.setId(university.getId());
            dto.setName(university.getName());
            dto.setAddress(this.addressShowConverter.toDto().apply(university.getAddress()));

            return dto;
        };
    }
}
