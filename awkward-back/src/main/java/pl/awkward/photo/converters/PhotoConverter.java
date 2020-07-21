package pl.awkward.photo.converters;

import org.springframework.stereotype.Service;
import pl.awkward.photo.dtos.PhotoDto;
import pl.awkward.photo.model_repo.Photo;
import pl.awkward.shared.BaseConverter;

import java.util.function.Function;

@Service
public class PhotoConverter extends BaseConverter<Photo, PhotoDto> {
    @Override
    public Function<PhotoDto, Photo> toEntity() {
        return dto -> {
            Photo photo = new Photo();
            convertIfNotNull(photo::setId, dto::getId);
            convertIfNotNull(photo::setPath, dto::getPath);
            convertIfNotNull(photo::setAddDate, dto::getAddDate);
            convertIfNotNull(photo::setArchive, dto::getArchive);
            convertIfNotNull(photo::setActive, dto::getActive);
            convertIfNotNull(photo::setUserId, dto::getUserId);
            return photo;
        };
    }

    @Override
    public Function<Photo, PhotoDto> toDto() {
        return photo -> {
            PhotoDto dto = new PhotoDto();
            convertIfNotNull(dto::setId, photo::getId);
            convertIfNotNull(dto::setPath, photo::getPath);
            convertIfNotNull(dto::setAddDate, dto::getAddDate);
            convertIfNotNull(dto::setArchive, photo::getArchive);
            convertIfNotNull(dto::setActive, photo::getActive);
            convertIfNotNull(dto::setUserId, photo::getId);
            return dto;
        };
    }
}
