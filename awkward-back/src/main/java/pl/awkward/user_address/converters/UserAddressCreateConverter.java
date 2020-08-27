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
            UserAddress userAddress = new UserAddress();
//            convertIfNotNull(userAddress::setUserId, dto::getUserId);
            convertIfNotNull(userAddress::setAddressId, dto::getAddressId);
            return userAddress;
        };
    }

    @Override
    public Function<UserAddress, UserAddressCreateDto> toDto() {
        return userAddress -> {
            UserAddressCreateDto dto = new UserAddressCreateDto();
//            convertIfNotNull(dto::setUserId, userAddress::getUserId);
            convertIfNotNull(dto::setAddressId, userAddress::getAddressId);
            return dto;
        };
    }
}
