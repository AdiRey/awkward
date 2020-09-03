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
            if (dto == null)
                return null;

            Liked liked = new Liked();

            liked.setFirstUser(dto.getFirstUser());
            liked.setSecondUser(dto.getFirstUser());

            liked.setFirstStatus(dto.getFirstStatus());
            liked.setSecondStatus(dto.getSecondStatus());

            return liked;
        };
    }

    @Override
    public Function<Liked, LikedDto> toDto() {
        return liked -> {
            if (liked == null)
                return null;

            LikedDto dto = new LikedDto();

            dto.setFirstUserId(liked.getFirstUser().getId());
            dto.setSecondUserId(liked.getSecondUser().getId());

            dto.setFirstStatus(liked.getFirstStatus());
            dto.setSecondStatus(liked.getSecondStatus());

            return dto;
        };
    }
}
