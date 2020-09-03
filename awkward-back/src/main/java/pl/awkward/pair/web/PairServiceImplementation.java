package pl.awkward.pair.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.awkward.pair.model_repo.Pair;
import pl.awkward.pair.model_repo.PairRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PairServiceImplementation implements PairService {

    private final PairRepository pairRepository;


    @Override
    public Page<Pair> getAll(int page, int size) {
        return this.pairRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Page<Pair> getAllByUserId(Long userId, int page, int size) {
        return this.pairRepository.findAllByLiked_FirstUserIdOrLiked_SecondUserId(userId, userId, PageRequest.of(page, size));
    }

    @Override
    public Pair save(Pair pair) {
        return this.pairRepository.save(pair);
    }

    @Override
    public void delete(Pair pair) {
        this.pairRepository.delete(pair);
    }

    @Override
    public Optional<Pair> findByTopicUUID(String uuid) {
        return this.pairRepository.findByTopic(uuid);
    }
}
