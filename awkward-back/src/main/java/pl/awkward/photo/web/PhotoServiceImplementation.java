package pl.awkward.photo.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.awkward.photo.model_repo.Photo;
import pl.awkward.photo.model_repo.PhotoRepository;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class PhotoServiceImplementation implements PhotoService {

    private final PhotoRepository photoRepository;
    private static final String PATH_PATTERN = "user_images/$1/$2";

    public PhotoServiceImplementation(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }


    @Override
    public Page<Photo> getAllByUserId(final Long userId, final int page, final int size, final boolean isActive) {
        return this.photoRepository.findAllByUserIdAndArchiveOrderByAddDateDesc(userId, isActive, PageRequest.of(page, size));
    }

    @Override
    public Photo save(Long userId, MultipartFile file) {
        Photo photo = new Photo();
        photo.setUserId(userId);
        photo.setAddDate(LocalDateTime.now());
        photo.setActive(true);
        try (final InputStream inputStream = file.getInputStream()) {

            final String final_path = PATH_PATTERN.replace("$1", String.valueOf(userId))
                    .replace("$2", UUID.randomUUID().toString().replace("-",""))
                    + "." +file.getContentType().substring(6);

            Files.copy(inputStream, Paths.get(final_path), StandardCopyOption.REPLACE_EXISTING);
            photo.setPath(final_path);
        } catch (IOException | InvalidPathException | NullPointerException e) {
            throw new IllegalArgumentException("There is unexpected error, please contact us.");
        }
        return this.photoRepository.save(photo);
    }

    @Override
    public Optional<Photo> getOnePhoto(final Long userId, final String filename) {
        return this.photoRepository.findByPath(
                PATH_PATTERN.replace("$1", String.valueOf(userId))
                        .replace("$2", filename)
        );
    }

    @Override
    public boolean updateActive(final Long photoId, final Boolean archive) {
        Optional<Photo> optionalPhoto = this.photoRepository.findById(photoId);
        if (optionalPhoto.isEmpty())
            return false;
        Photo photo = optionalPhoto.get();
        photo.setArchive(archive);
        return true;
    }
}
