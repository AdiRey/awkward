package pl.awkward.photo.web;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;
import pl.awkward.photo.model_repo.Photo;

public interface PhotoService {
    Page<Photo> getAllByUserId(Long userId, int page, int size, boolean isActive);
    Photo save(Long userId, Integer position, MultipartFile file);
    boolean updateActive(Long photoId, Boolean archive);
}
