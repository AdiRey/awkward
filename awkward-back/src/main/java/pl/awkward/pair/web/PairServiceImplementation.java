package pl.awkward.pair.web;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.awkward.exceptions.DuplicateException;
import pl.awkward.pair.model_repo.Pair;
import pl.awkward.pair.model_repo.PairRepository;

import java.util.Optional;

@Service
public class PairServiceImplementation implements PairService {

    private final PairRepository pairRepository;


    public PairServiceImplementation(PairRepository pairRepository) {
        this.pairRepository = pairRepository;
    }

    @Override
    @Transactional
    public void acceptableIds(Long firstId, Long secondId) {
        Optional<Pair> optionalFirst = this.pairRepository.findByUserIdFirstAndUserIdSecond(firstId, secondId);
        Optional<Pair> optionalSecond = this.pairRepository.findByUserIdFirstAndUserIdSecond(secondId, firstId);

        if (optionalFirst.isPresent() || optionalSecond.isPresent())
            throw new DuplicateException("This pair is already exists");
    }
}
