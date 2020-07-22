package pl.awkward.user_interest.converters;

import org.springframework.stereotype.Service;
import pl.awkward.shared.BaseConverter;
import pl.awkward.user_interest.dtos.UserInterestDto;
import pl.awkward.user_interest.model_repo.UserInterest;

import java.util.function.Function;

@Service
public class UserInterestConverter extends BaseConverter<UserInterest, UserInterestDto> {
    @Override
    public Function<UserInterestDto, UserInterest> toEntity() {
        return dto -> {
            UserInterest ui = new UserInterest();
            convertIfNotNull(ui::setId, dto::getId);
            convertIfNotNull(ui::setUserId, dto::getUserId);
            convertIfNotNull(ui::setInterestId, dto::getInterestId);
            convertIfNotNull(ui::setActive, dto::getActive);
            return ui;
        };
    }

    @Override
    public Function<UserInterest, UserInterestDto> toDto() {
        return ui -> {
            UserInterestDto dto = new UserInterestDto();
            convertIfNotNull(dto::setId, ui::getId);
            convertIfNotNull(dto::setUserId, ui::getUserId);
            convertIfNotNull(dto::setInterestId, ui::getInterestId);
            convertIfNotNull(dto::setActive, ui::getActive);
            return dto;
        };
    }
}
