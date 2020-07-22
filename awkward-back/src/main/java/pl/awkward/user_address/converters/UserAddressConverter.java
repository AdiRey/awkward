package pl.awkward.user_address.converters;

import org.springframework.stereotype.Service;
import pl.awkward.shared.BaseConverter;
import pl.awkward.user_address.dtos.UserAddressDto;
import pl.awkward.user_address.model_repo.UserAddress;

import java.util.function.Function;

@Service
public class UserAddressConverter extends BaseConverter<UserAddress, UserAddressDto> {
    @Override
    public Function<UserAddressDto, UserAddress> toEntity() {
        return dto -> {
            UserAddress userAddress = new UserAddress();
            convertIfNotNull(userAddress::setId, dto::getId);
            convertIfNotNull(userAddress::setUserId, dto::getUserId);
            convertIfNotNull(userAddress::setAddressId, dto::getAddressId);
            convertIfNotNull(userAddress::setActive, dto::getActive);
            return userAddress;
        };
    }

    @Override
    public Function<UserAddress, UserAddressDto> toDto() {
        return userAddress -> {
            UserAddressDto dto = new UserAddressDto();
            convertIfNotNull(dto::setId, userAddress::getId);
            convertIfNotNull(dto::setUserId, userAddress::getUserId);
            convertIfNotNull(dto::setAddressId, userAddress::getAddressId);
            convertIfNotNull(dto::setActive, userAddress::getActive);
            return dto;
        };
    }
}
