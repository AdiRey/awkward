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
            if (dto == null)
                return null;

            Address address = new Address();

            address.setId(dto.getId());
            address.setCountry(dto.getCountry());
            address.setCity(dto.getCity());
            address.setActive(dto.getActive());
            address.setAddDate(dto.getAddDate());
            address.setDeleteDate(dto.getDeleteDate());

            return address;
        };
    }

    @Override
    public Function<Address, AddressDto> toDto() {
        return address -> {
            if (address == null)
                return null;

            AddressDto dto = new AddressDto();

            dto.setId(address.getId());
            dto.setCountry(address.getCountry());
            dto.setCity(address.getCity());
            dto.setActive(address.getActive());
            dto.setAddDate(address.getAddDate());
            dto.setDeleteDate(address.getDeleteDate());

            return dto;
        };
    }
}
