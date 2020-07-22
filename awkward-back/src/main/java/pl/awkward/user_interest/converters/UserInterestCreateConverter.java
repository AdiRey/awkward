package pl.awkward.user_interest.converters;

import org.springframework.stereotype.Service;
import pl.awkward.shared.BaseConverter;
import pl.awkward.user_interest.dtos.UserInterestCreateDto;
import pl.awkward.user_interest.model_repo.UserInterest;

import java.util.function.Function;

@Service
public class UserInterestCreateConverter extends BaseConverter<UserInterest, UserInterestCreateDto> {
    @Override
    public Function<UserInterestCreateDto, UserInterest> toEntity() {
        return dto -> {
            UserInterest ui = new UserInterest();
            convertIfNotNull(ui::setUserId, dto::getUserId);
            convertIfNotNull(ui::setInterestId, dto::getInterestId);
            return ui;
        };
    }

    @Override
    public Function<UserInterest, UserInterestCreateDto> toDto() {
        return ui -> {
            UserInterestCreateDto dto = new UserInterestCreateDto();
            convertIfNotNull(dto::setUserId, ui::getUserId);
            convertIfNotNull(dto::setInterestId, ui::getInterestId);
            return dto;
        };
    }
}
