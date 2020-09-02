package pl.awkward.user_address.converters;

import org.springframework.stereotype.Service;
import pl.awkward.shared.BaseConverter;
import pl.awkward.user_address.dtos.UserAddressCreateDto;
import pl.awkward.user_address.model_repo.UserAddress;

import java.util.function.Function;

@Service
public class UserAddressCreateConverter extends BaseConverter<UserAddress, UserAddressCreateDto> {

    @Override
    public Function<UserAddressCreateDto, UserAddress> toEntity() {
        return dto -> {
            if (dto == null)
                return null;

            UserAddress userAddress = new UserAddress();

            userAddress.getEmbeddedIds().setUserId(dto.getUserId());
            userAddress.getEmbeddedIds().setAddressId(dto.getAddressId());
            userAddress.setPosition(dto.getPosition());
            userAddress.setTimeInPercentage(dto.getTimeInPercentage());

            return userAddress;
        };
    }

    @Override
    public Function<UserAddress, UserAddressCreateDto> toDto() {
        return userAddress -> {
            if (userAddress == null)
                return null;

            UserAddressCreateDto dto = new UserAddressCreateDto();

            dto.setUserId(userAddress.getEmbeddedIds().getUserId());
            dto.setAddressId(userAddress.getEmbeddedIds().getAddressId());
            dto.setPosition(userAddress.getPosition());
            dto.setTimeInPercentage(userAddress.getTimeInPercentage());

            return dto;
        };
    }
}
