package pl.awkward.photo.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.awkward.address.dtos.AddressShowDto;
import pl.awkward.address.model_repo.Address;
import pl.awkward.photo.dtos.PhotoShowDto;
import pl.awkward.photo.model_repo.Photo;
import pl.awkward.shared.BaseConverter;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class PhotoShowConverter extends BaseConverter<Photo, PhotoShowDto> {

    private final BaseConverter<Address, AddressShowDto> addressShowConverter;

    @Override
    public Function<PhotoShowDto, Photo> toEntity() {
        return dto -> {
            if (dto == null)
                return null;

            Photo photo = new Photo();

            photo.setId(dto.getId());
            photo.setPath(dto.getPath());
            photo.setArchive(dto.getArchive());
            photo.setActive(dto.getActive());
            photo.setAddDate(dto.getAddDate());
            photo.setDeleteDate(dto.getDeleteDate());
            photo.setPosition(dto.getPosition());
            photo.setAddress(this.addressShowConverter.toEntity().apply(dto.getAddress()));

            return photo;
        };
    }

    @Override
    public Function<Photo, PhotoShowDto> toDto() {
        return photo -> {
            if (photo == null)
                return null;

            PhotoShowDto dto = new PhotoShowDto();

            dto.setId(photo.getId());
            dto.setPath(photo.getPath());
            dto.setArchive(photo.getArchive());
            dto.setActive(photo.getActive());
            dto.setAddDate(photo.getAddDate());
            dto.setDeleteDate(photo.getDeleteDate());
            dto.setPosition(photo.getPosition());
            dto.setAddress(this.addressShowConverter.toDto().apply(photo.getAddress()));

            return dto;
        };
    }
}
