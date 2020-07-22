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
            Interest interest = new Interest();
            convertIfNotNull(interest::setId, dto::getId);
            convertIfNotNull(interest::setName, dto::getName);
            convertIfNotNull(interest::setActive, dto::getActive);
            return interest;
        };
    }

    @Override
    public Function<Interest, InterestDto> toDto() {
        return interest -> {
            InterestDto dto = new InterestDto();
            convertIfNotNull(dto::setId, interest::getId);
            convertIfNotNull(dto::setName, interest::getName);
            convertIfNotNull(dto::setActive, interest::getActive);
            return dto;
        };
    }
}
