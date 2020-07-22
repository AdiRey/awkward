package pl.awkward.pair.converters;

import org.springframework.stereotype.Service;
import pl.awkward.pair.dtos.PairDto;
import pl.awkward.pair.model_repo.Pair;
import pl.awkward.shared.BaseConverter;

import java.util.function.Function;

@Service
public class PairConverter extends BaseConverter<Pair, PairDto> {
    @Override
    public Function<PairDto, Pair> toEntity() {
        return dto -> {
            Pair pair = new Pair();
            convertIfNotNull(pair::setId, dto::getId);
            convertIfNotNull(pair::setUserIdFirst, dto::getUserIdFirst);
            convertIfNotNull(pair::setUserIdSecond, dto::getUserIdSecond);
            convertIfNotNull(pair::setActive, dto::getActive);
            return pair;
        };
    }

    @Override
    public Function<Pair, PairDto> toDto() {
        return pair -> {
            PairDto dto = new PairDto();
            convertIfNotNull(dto::setId, pair::getId);
            convertIfNotNull(dto::setUserIdFirst, pair::getUserIdFirst);
            convertIfNotNull(dto::setUserIdSecond, pair::getUserIdSecond);
            convertIfNotNull(dto::setActive, pair::getActive);
            return dto;
        };
    }
}
