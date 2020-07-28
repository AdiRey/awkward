package pl.awkward.liked.web;

import org.springframework.data.domain.Page;
import pl.awkward.liked.model_repo.Liked;

public interface LikedService {
    Liked save(Liked liked);
    boolean canBeCouple(Long firstId, Long secondId);
    Page<Liked> getAllPagination(Long userId, int page, int size, boolean isActive);
}
