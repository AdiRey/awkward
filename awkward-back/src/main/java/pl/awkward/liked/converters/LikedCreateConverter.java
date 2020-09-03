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
            if (dto == null)
                return null;

            Liked liked = new Liked();

            liked.setFirstUser(dto.getFirstUser());
            liked.setSecondUser(dto.getSecondUser());

            convertIfNotNull(liked::setFirstStatus, dto::getStatus);
            convertIfNotNull(liked::setSecondStatus, dto::getSecondStatus);

            return liked;
        };
    }

    @Override
    public Function<Liked, LikedCreateDto> toDto() {
        return liked -> {
            if (liked == null)
                return null;

            LikedCreateDto dto = new LikedCreateDto();

            dto.setFirstUserId(liked.getFirstUser().getId());
            dto.setSecondUserId(liked.getSecondUser().getId());

            convertIfNotNull(dto::setStatus, liked::getFirstStatus);
            convertIfNotNull(dto::setSecondStatus, liked::getSecondStatus);

            return dto;
        };
    }
}
