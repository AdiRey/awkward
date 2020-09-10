package pl.awkward.address.converters;

import org.springframework.stereotype.Service;
import pl.awkward.address.dtos.AddressShowDto;
import pl.awkward.address.model_repo.Address;
import pl.awkward.shared.baseStuff.BaseConverter;

import java.util.function.Function;

@Service
public class AddressShowConverter extends BaseConverter<Address, AddressShowDto> {

    @Override
    public Function<AddressShowDto, Address> toEntity() {
        return dto -> {
            if (dto == null)
                return null;

            Address address = new Address();

            address.setId(dto.getId());
            address.setCountry(dto.getCountry());
            address.setCity(dto.getCity());

            return address;
        };
    }

    @Override
    public Function<Address, AddressShowDto> toDto() {
        return address -> {
            if (address == null)
                return null;

            AddressShowDto dto = new AddressShowDto();

            dto.setId(address.getId());
            dto.setCountry(address.getCountry());
            dto.setCity(address.getCity());

            return dto;
        };
    }
}
