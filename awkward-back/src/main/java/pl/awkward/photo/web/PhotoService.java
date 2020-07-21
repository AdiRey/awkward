package pl.awkward.photo.web;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;
import pl.awkward.photo.model_repo.Photo;

import java.util.Optional;

public interface PhotoService {
    Page<Photo> getAllByUserId(Long userId, int page, int size, boolean isActive);
    Photo save(Long userId, MultipartFile file);
    Optional<Photo> getOnePhoto(Long userId, String filename);
    boolean updateActive(Long photoId, Boolean archive);
}
