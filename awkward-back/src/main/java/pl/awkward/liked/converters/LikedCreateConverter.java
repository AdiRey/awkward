package pl.awkward.liked.converters;

import org.springframework.stereotype.Service;
import pl.awkward.liked.dtos.LikedCreateDto;
import pl.awkward.liked.model_repo.Liked;
import pl.awkward.shared.BaseConverter;

import java.util.function.Function;

@Service
public class LikedCreateConverter extends BaseConverter<Liked, LikedCreateDto> {
    @Override
    public Function<LikedCreateDto, Liked> toEntity() {
        return dto -> {
            Liked liked = new Liked();
            convertIfNotNull(liked::setStatus, dto::getStatus);
            convertIfNotNull(liked::setDate, dto::getDate);
            convertIfNotNull(liked::setUserId, dto::getUserId);
            convertIfNotNull(liked::setSecondUserId, dto::getSecondUserId);
            return liked;
        };
    }

    @Override
    public Function<Liked, LikedCreateDto> toDto() {
        return liked -> {
            LikedCreateDto dto = new LikedCreateDto();
            convertIfNotNull(dto::setStatus, liked::getStatus);
            convertIfNotNull(dto::setDate, liked::getDate);
            convertIfNotNull(dto::setUserId, liked::getUserId);
            convertIfNotNull(dto::setSecondUserId, liked::getSecondUserId);
            return dto;
        };
    }
}
