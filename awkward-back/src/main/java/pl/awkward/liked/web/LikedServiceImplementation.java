package pl.awkward.liked.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.awkward.liked.model_repo.Liked;
import pl.awkward.liked.model_repo.LikedRepository;
import pl.awkward.user.model_repo.User;

@Service
public class LikedServiceImplementation implements LikedService{

    private final LikedRepository likedRepository;

    public LikedServiceImplementation(LikedRepository likedRepository) {
        this.likedRepository = likedRepository;
    }

    @Override
    public Liked save(Liked liked) {
        if (liked.getFirstUser().getId() > liked.getSecondUser().getId()) {
            Byte temp = liked.getFirstStatus();
            User user = liked.getFirstUser();

            liked.setFirstStatus(liked.getSecondStatus());
            liked.setFirstStatus(temp);

            liked.setFirstUser(liked.getSecondUser());
            liked.setSecondUser(user);
        }

        return this.likedRepository.save(liked);
    }

    @Override
    public Page<Liked> getAllPagination(Long firstUserId, Long secondUserId, int page, int size) {
        return this.likedRepository
                .findAllByFirstUserOrSecondUserId(
                        firstUserId,
                        secondUserId,
                        PageRequest.of(page, size)
                );
    }
}
