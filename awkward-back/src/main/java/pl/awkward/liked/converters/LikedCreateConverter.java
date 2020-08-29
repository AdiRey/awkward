package pl.awkward.liked.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.awkward.liked.dtos.LikedCreateDto;
import pl.awkward.liked.model_repo.Liked;
import pl.awkward.shared.BaseConverter;
import pl.awkward.user.model_repo.User;

import javax.persistence.EntityManager;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class LikedCreateConverter extends BaseConverter<Liked, LikedCreateDto> {

    private final EntityManager entityManager;

    @Override
    public Function<LikedCreateDto, Liked> toEntity() {
        return dto -> {
            if (dto == null)
                return null;

            Liked liked = new Liked();

            liked.setFirstUser(this.entityManager.getReference(User.class, dto.getFirstUserId()));
            liked.setSecondUser(this.entityManager.getReference(User.class, dto.getSecondUserId()));

            convertIfNotNull(liked::setFirstStatus, dto::getFirstStatus);
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

            convertIfNotNull(dto::setFirstStatus, liked::getFirstStatus);
            convertIfNotNull(dto::setSecondStatus, liked::getSecondStatus);

            return dto;
        };
    }
}
