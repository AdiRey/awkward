package pl.awkward.interest.converters;

import org.springframework.stereotype.Service;
import pl.awkward.interest.dtos.InterestCreateUpdateDto;
import pl.awkward.interest.model_repo.Interest;
import pl.awkward.shared.BaseConverter;

import java.util.function.Function;

@Service
public class InterestCreateUpdateConverter extends BaseConverter<Interest, InterestCreateUpdateDto> {

    @Override
    public Function<InterestCreateUpdateDto, Interest> toEntity() {
        return dto -> {
            Interest interest = new Interest();
            convertIfNotNull(interest::setName, dto::getName);
            return interest;
        };
    }

    @Override
    public Function<Interest, InterestCreateUpdateDto> toDto() {
        return interest -> {
            InterestCreateUpdateDto dto = new InterestCreateUpdateDto();
            convertIfNotNull(dto::setName, interest::getName);
            return dto;
        };
    }
}
