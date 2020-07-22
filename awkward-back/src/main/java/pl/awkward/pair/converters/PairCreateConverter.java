package pl.awkward.pair.converters;

import org.springframework.stereotype.Service;
import pl.awkward.pair.dtos.PairCreateDto;
import pl.awkward.pair.model_repo.Pair;
import pl.awkward.shared.BaseConverter;

import java.util.function.Function;

@Service
public class PairCreateConverter extends BaseConverter<Pair, PairCreateDto> {
    @Override
    public Function<PairCreateDto, Pair> toEntity() {
        return dto -> {
            Pair pair = new Pair();
            convertIfNotNull(pair::setUserIdFirst, dto::getUserIdFirst);
            convertIfNotNull(pair::setUserIdSecond, dto::getUserIdSecond);
            return pair;
        };
    }

    @Override
    public Function<Pair, PairCreateDto> toDto() {
        return pair -> {
            PairCreateDto dto = new PairCreateDto();
            convertIfNotNull(dto::setUserIdFirst, pair::getUserIdFirst);
            convertIfNotNull(dto::setUserIdSecond, pair::getUserIdSecond);
            return dto;
        };
    }
}
