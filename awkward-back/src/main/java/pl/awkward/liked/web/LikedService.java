package pl.awkward.liked.web;

import org.springframework.data.domain.Page;
import pl.awkward.liked.model_repo.Liked;

public interface LikedService {

    Liked save(Liked liked);

    Page<Liked> getAllPagination(Long firstUserId, Long secondUserId, int page, int size);

}
