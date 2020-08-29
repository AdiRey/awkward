package pl.awkward.interest.converters;

import org.springframework.stereotype.Service;
import pl.awkward.interest.dtos.InterestShowDto;
import pl.awkward.interest.model_repo.Interest;
import pl.awkward.shared.BaseConverter;

import java.util.function.Function;

@Service
public class InterestShowConverter extends BaseConverter<Interest, InterestShowDto> {

    @Override
    public Function<InterestShowDto, Interest> toEntity() {
        return dto -> {
            if (dto == null)
                return null;

            Interest interest = new Interest();

            interest.setId(dto.getId());
            interest.setName(dto.getName());

            return interest;
        };
    }

    @Override
    public Function<Interest, InterestShowDto> toDto() {
        return interest -> {
            if (interest == null)
                return null;

            InterestShowDto dto = new InterestShowDto();

            dto.setId(interest.getId());
            dto.setName(interest.getName());

            return dto;
        };
    }
}
