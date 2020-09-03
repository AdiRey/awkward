package pl.awkward.pair.converters;

import org.springframework.stereotype.Service;
import pl.awkward.liked.model_repo.UserIdsKey;
import pl.awkward.pair.dtos.PairCreateDto;
import pl.awkward.pair.model_repo.Pair;
import pl.awkward.shared.BaseConverter;

import java.util.function.Function;

@Service
public class PairCreateConverter extends BaseConverter<Pair, PairCreateDto> {
    @Override
    public Function<PairCreateDto, Pair> toEntity() {
        return dto -> {
            if (dto == null)
                return null;

            Pair pair = new Pair();

            UserIdsKey id = new UserIdsKey();

            id.setFirstUserId(dto.getFirstUserId());
            id.setSecondUserId(dto.getSecondUserId());

            pair.setId(id);
            pair.setStatus(dto.getStatus());

            return pair;
        };
    }

    @Override
    public Function<Pair, PairCreateDto> toDto() {
        return pair -> {
            if (pair == null)
                return null;

            PairCreateDto dto = new PairCreateDto();

            dto.setFirstUserId(pair.getId().getFirstUserId());
            dto.setSecondUserId(pair.getId().getSecondUserId());
            dto.setStatus(pair.getStatus());

            return dto;
        };
    }
}
