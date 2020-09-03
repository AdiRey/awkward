package pl.awkward.pair.converters;

import org.springframework.stereotype.Service;
import pl.awkward.liked.model_repo.UserIdsKey;
import pl.awkward.pair.dtos.PairDto;
import pl.awkward.pair.model_repo.Pair;
import pl.awkward.shared.BaseConverter;

import java.util.function.Function;

@Service
public class PairConverter extends BaseConverter<Pair, PairDto> {
    @Override
    public Function<PairDto, Pair> toEntity() {
        return dto -> {
            if (dto == null)
                return null;

            Pair pair = new Pair();

            UserIdsKey id = new UserIdsKey();

            id.setFirstUserId(dto.getFirstUserId());
            id.setSecondUserId(dto.getSecondUserId());

            pair.setId(id);
            pair.setTopic(dto.getTopic());
            pair.setAddDate(dto.getAddDate());
            pair.setStatus(dto.getStatus());

            return pair;
        };
    }

    @Override
    public Function<Pair, PairDto> toDto() {
        return pair -> {
            if (pair == null)
                return null;

            PairDto dto = new PairDto();

            dto.setFirstUserId(pair.getId().getFirstUserId());
            dto.setSecondUserId(pair.getId().getSecondUserId());
            dto.setTopic(pair.getTopic());
            dto.setAddDate(pair.getAddDate());
            dto.setStatus(pair.getStatus());

            return dto;
        };
    }
}
