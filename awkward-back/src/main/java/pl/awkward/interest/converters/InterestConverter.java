package pl.awkward.interest.converters;

import org.springframework.stereotype.Service;
import pl.awkward.interest.dtos.InterestDto;
import pl.awkward.interest.model_repo.Interest;
import pl.awkward.shared.BaseConverter;

import java.util.function.Function;

@Service
public class InterestConverter extends BaseConverter<Interest, InterestDto> {
    @Override
    public Function<InterestDto, Interest> toEntity() {
        return dto -> {
            if (dto == null)
                return null;

            Interest interest = new Interest();

            interest.setId(dto.getId());
            interest.setName(dto.getName());
            interest.setActive(dto.getActive());
            interest.setAddDate(dto.getAddDate());
            interest.setDeleteDate(dto.getDeleteDate());

            return interest;
        };
    }

    @Override
    public Function<Interest, InterestDto> toDto() {
        return interest -> {
            if (interest == null)
                return null;

            InterestDto dto = new InterestDto();

            dto.setId(interest.getId());
            dto.setName(interest.getName());
            dto.setActive(interest.getActive());
            dto.setAddDate(interest.getAddDate());
            dto.setDeleteDate(interest.getDeleteDate());

            return dto;
        };
    }
}
