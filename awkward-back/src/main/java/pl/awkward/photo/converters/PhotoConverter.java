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
            if (dto == null)
                return null;

            Photo photo = new Photo();

            photo.setId(dto.getId());
            photo.setPath(dto.getPath());
            photo.setPosition(dto.getPosition());
            photo.setArchive(dto.getArchive());
            photo.setAddDate(dto.getAddDate());
            photo.setAddress(dto.getAddress());

            return photo;
        };
    }

    @Override
    public Function<Photo, PhotoDto> toDto() {
        return photo -> {
            if (photo == null)
                return null;

            PhotoDto dto = new PhotoDto();

            dto.setId(photo.getId());
            dto.setPath(photo.getPath());
            dto.setPosition(photo.getPosition());
            dto.setArchive(photo.getArchive());
            dto.setAddDate(photo.getAddDate());
            dto.setAddress(photo.getAddress());

            dto.setAddressId(photo.getAddress().getId());

            return dto;
        };
    }
}
