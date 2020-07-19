package pl.awkward.address.converters;

import org.springframework.stereotype.Service;
import pl.awkward.address.dtos.AddressDto;
import pl.awkward.address.model_repo.Address;
import pl.awkward.shared.BaseConverter;

import java.util.function.Function;

@Service
public class AddressConverter extends BaseConverter<Address, AddressDto> {
    @Override
    public Function<AddressDto, Address> toEntity() {
        return dto -> {
            Address address = new Address();
            address.setId(dto.getId());
            address.setCountry(dto.getCountry());
            address.setCity(dto.getCity());
            address.setActive(dto.getActive());
            return address;
        };
    }

    @Override
    public Function<Address, AddressDto> toDto() {
        return address -> {
            AddressDto dto = new AddressDto();
            dto.setId(address.getId());
            dto.setCountry(address.getCountry());
            dto.setCity(address.getCity());
            dto.setActive(address.getActive());
            return dto;
        };
    }
}
