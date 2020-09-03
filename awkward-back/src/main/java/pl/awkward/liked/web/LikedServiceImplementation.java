package pl.awkward.liked.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import pl.awkward.liked.model_repo.Liked;
import pl.awkward.liked.model_repo.LikedRepository;
import pl.awkward.user.model_repo.User;

import java.util.Optional;

@Service
public class LikedServiceImplementation implements LikedService {

    private final LikedRepository likedRepository;

    public LikedServiceImplementation(LikedRepository likedRepository) {
        this.likedRepository = likedRepository;
    }

    @Override
    public Liked save(Liked liked) {
        this.ifFirstIdIsBiggerThanSecond(liked);

        return this.likedRepository.save(liked);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Liked update(Liked liked) {
        this.ifFirstIdIsBiggerThanSecond(liked);

        Optional<Liked> optionalLiked = this.likedRepository
                .findById_FirstUserIdAndId_SecondUserId(liked.getFirstUser().getId(), liked.getSecondUser().getId());

        if (optionalLiked.isEmpty())
            throw new IllegalArgumentException("Niespodziewany błąd.");

        Liked bdLike = optionalLiked.get();

        if (liked.getFirstStatus() != null)
            bdLike.setFirstStatus(liked.getFirstStatus());
        if (liked.getSecondStatus() != null)
            bdLike.setSecondStatus(liked.getSecondStatus());

        return bdLike;
    }

    @Override
    public Page<Liked> getAllPagination(Long userId, int page, int size) {
        return this.likedRepository
                .findAllById_FirstUserIdOrId_SecondUserId(
                        userId,
                        userId,
                        PageRequest.of(page, size)
                );
    }

    @Override
    public boolean exists(Liked liked) {
        this.ifFirstIdIsBiggerThanSecond(liked);

        return this.likedRepository
                .findById_FirstUserIdAndId_SecondUserId(liked.getFirstUser().getId(), liked.getSecondUser().getId())
                .isPresent();
    }

    private void ifFirstIdIsBiggerThanSecond(Liked liked) {
        if (liked.getFirstUser().getId() > liked.getSecondUser().getId()) {
            Byte temp = liked.getFirstStatus();
            User user = liked.getFirstUser();

            liked.setFirstStatus(liked.getSecondStatus());
            liked.setSecondStatus(temp);

            liked.setFirstUser(liked.getSecondUser());
            liked.setSecondUser(user);
        }
    }
}
