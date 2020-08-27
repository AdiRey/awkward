package pl.awkward.liked.converters;

import org.springframework.stereotype.Service;
import pl.awkward.liked.dtos.LikedDto;
import pl.awkward.liked.model_repo.Liked;
import pl.awkward.shared.BaseConverter;

import java.util.function.Function;

@Service
public class LikedConverter extends BaseConverter<Liked, LikedDto> {
    @Override
    public Function<LikedDto, Liked> toEntity() {
        return dto -> {
            Liked liked = new Liked();
            convertIfNotNull(liked::setId, dto::getId);
            convertIfNotNull(liked::setStatus, dto::getStatus);
            convertIfNotNull(liked::setDate, dto::getDate);
            convertIfNotNull(liked::setActive, dto::getActive);
//            convertIfNotNull(liked::setUserId, dto::getUserId);
//            convertIfNotNull(liked::setSecondUserId, dto::getSecondUserId);
            return liked;
        };
    }

    @Override
    public Function<Liked, LikedDto> toDto() {
        return liked -> {
            LikedDto dto = new LikedDto();
            convertIfNotNull(dto::setId, liked::getId);
            convertIfNotNull(dto::setStatus, liked::getStatus);
            convertIfNotNull(dto::setDate, liked::getDate);
            convertIfNotNull(dto::setActive, liked::getActive);
//            convertIfNotNull(dto::setUserId, liked::getUserId);
//            convertIfNotNull(dto::setSecondUserId, liked::getSecondUserId);
            return dto;
        };
    }
}
