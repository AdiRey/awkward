package pl.awkward.address.converters;

import org.springframework.stereotype.Service;
import pl.awkward.address.dtos.AddressCreateUpdateDto;
import pl.awkward.address.model_repo.Address;
import pl.awkward.shared.BaseConverter;

import java.util.function.Function;

@Service
public class AddressCreateUpdateConverter extends BaseConverter<Address, AddressCreateUpdateDto> {

    @Override
    public Function<AddressCreateUpdateDto, Address> toEntity() {
        return dto -> {
            if (dto == null)
                return null;

            Address address = new Address();

            address.setCountry(dto.getCountry());
            address.setCity(dto.getCity());

            return address;
        };
    }

    @Override
    public Function<Address, AddressCreateUpdateDto> toDto() {
        return address -> {
            if (address == null)
                return null;

            AddressCreateUpdateDto dto = new AddressCreateUpdateDto();

            dto.setCountry(address.getCountry());
            dto.setCity(address.getCity());

            return dto;
        };
    }
}
