package pl.awkward.user_address.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.awkward.address.dtos.AddressShowDto;
import pl.awkward.address.model_repo.Address;
import pl.awkward.shared.BaseConverter;
import pl.awkward.user_address.dtos.UserAddressDto;
import pl.awkward.user_address.model_repo.UserAddress;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class UserAddressConverter extends BaseConverter<UserAddress, UserAddressDto> {

    private final BaseConverter<Address, AddressShowDto> addressConverter;

    @Override
    public Function<UserAddressDto, UserAddress> toEntity() {
        return dto -> {
            if (dto == null)
                return null;

            UserAddress userAddress = new UserAddress();

            userAddress.getEmbeddedIds().setUserId(dto.getUserId());
            userAddress.setAddress(this.addressConverter.toEntity().apply(dto.getAddress()));
            userAddress.setPosition(dto.getPosition());
            userAddress.setPosition(dto.getTimeInPercentage());

            return userAddress;
        };
    }

    @Override
    public Function<UserAddress, UserAddressDto> toDto() {
        return userAddress -> {
            if (userAddress == null)
                return null;

            UserAddressDto dto = new UserAddressDto();

            dto.setUserId(userAddress.getEmbeddedIds().getUserId());
            dto.setAddress(this.addressConverter.toDto().apply(userAddress.getAddress()));
            dto.setPosition(userAddress.getPosition());
            dto.setTimeInPercentage(userAddress.getTimeInPercentage());

            return dto;
        };
    }
}
