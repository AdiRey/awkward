package pl.awkward.photo.web;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;
import pl.awkward.photo.model_repo.Photo;
import pl.awkward.user.model_repo.User;

public interface PhotoService {

    Page<Photo> getAllByUserId(Long userId, int page, int size, boolean isArchive);

    Photo save(Long userId, MultipartFile file);

    boolean updateArchive(Long photoId, Boolean archive);

}
