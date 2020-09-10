package pl.awkward.university.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.awkward.address.dtos.AddressDto;
import pl.awkward.address.model_repo.Address;
import pl.awkward.shared.baseStuff.BaseConverter;
import pl.awkward.university.dtos.UniversityDto;
import pl.awkward.university.model_repo.University;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class UniversityConverter extends BaseConverter<University, UniversityDto> {

    private final BaseConverter<Address, AddressDto> addressDto;

    @Override
    public Function<UniversityDto, University> toEntity() {
        return dto -> {
            if (dto == null)
                return null;

            University university = new University();

            university.setId(dto.getId());
            university.setName(dto.getName());
            university.setAddress(this.addressDto.toEntity().apply(dto.getAddress()));
            university.setAddDate(dto.getAddDate());
            university.setDeleteDate(dto.getDeleteDate());
            university.setActive(dto.getActive());

            return university;
        };
    }

    @Override
    public Function<University, UniversityDto> toDto() {
        return university -> {
            if (university == null)
                return null;

            UniversityDto dto = new UniversityDto();

            dto.setId(university.getId());
            dto.setName(university.getName());
            dto.setAddress(this.addressDto.toDto().apply(university.getAddress()));
            dto.setAddDate(university.getAddDate());
            dto.setDeleteDate(university.getDeleteDate());
            dto.setActive(university.getActive());

            return dto;
        };
    }
}
