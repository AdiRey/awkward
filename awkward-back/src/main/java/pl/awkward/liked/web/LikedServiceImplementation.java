package pl.awkward.liked.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.awkward.liked.model_repo.Liked;
import pl.awkward.liked.model_repo.LikedRepository;

import java.time.LocalDateTime;

@Service
public class LikedServiceImplementation implements LikedService{

    private final LikedRepository likedRepository;

    public LikedServiceImplementation(LikedRepository likedRepository) {
        this.likedRepository = likedRepository;
    }

    @Override
    public Liked save(Liked liked) {
        liked.setDate(LocalDateTime.now());
        return this.likedRepository.save(liked);
    }

    @Override
    public boolean canBeCouple(Long firstId, Long secondId) {
        return this.likedRepository.findByUserIdAndSecondUserId(firstId, secondId).isPresent();
    }

    @Override
    public Page<Liked> getAllPagination(Long userId, int page, int size, boolean isActive) {
        return this.likedRepository.findAllByUserIdAndActive(userId, isActive, PageRequest.of(page, size));
    }
}
