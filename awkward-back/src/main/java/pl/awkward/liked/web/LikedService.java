package pl.awkward.liked.web;

import pl.awkward.liked.model_repo.Liked;

public interface LikedService {
    Liked save(Liked liked);
    boolean canBeCouple(Long firstId, Long secondId);
}
