package pl.awkward.address.web;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.awkward.address.dtos.AddressCreateUpdateDto;
import pl.awkward.address.dtos.AddressDto;
import pl.awkward.address.model_repo.Address;
import pl.awkward.shared.BaseConverter;
import pl.awkward.shared.BaseCrudController;
import pl.awkward.shared.BaseRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/addresses")
public class AddressController extends BaseCrudController<Address> {

    private final BaseConverter<Address, AddressDto> addressConverter;
    private final BaseConverter<Address, AddressCreateUpdateDto> addressCreateUpdateConverter;
    private final AddressService addressService;


    public AddressController(BaseRepository<Address> repository,
                             BaseConverter<Address, AddressDto> addressConverter,
                             BaseConverter<Address, AddressCreateUpdateDto> addressCreateUpdateConverter,
                             final AddressService addressService) {
        super(repository);
        this.addressConverter = addressConverter;
        this.addressCreateUpdateConverter = addressCreateUpdateConverter;
        this.addressService = addressService;
    }

    @GetMapping("")
    public ResponseEntity<Page<AddressDto>> getAll(@RequestParam(defaultValue = "0") final int page,
                                                   @RequestParam(defaultValue = "20") final int size,
                                                   @RequestParam(defaultValue = "id") final String column,
                                                   @RequestParam(defaultValue = "ASC") final String direction,
                                                   @RequestParam(defaultValue = "") final String filter) {
        if (filter.equals(""))
            return super.getAll(page, size, column, direction, this.addressConverter.toDto());
        return ResponseEntity
                .ok(this.addressService.getAllWithFilter(page, size, column, direction, filter)
                        .map(this.addressConverter.toDto())
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> getOne(@PathVariable final Long id) {
        return super.getOne(id, this.addressConverter.toDto());
    }

    @PostMapping("")
    public ResponseEntity<Void> create(@RequestBody @Valid final AddressCreateUpdateDto dto) {
        this.addressService.acceptableCountryAndCity(dto.getCountry(), dto.getCity());
        return super.create(dto, this.addressCreateUpdateConverter.toEntity());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        return super.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable final Long id, @RequestBody @Valid final AddressCreateUpdateDto dto) {
        this.addressService.acceptableCountryAndCity(dto.getCountry(), dto.getCity());
        boolean status = this.addressService.update(id, this.addressCreateUpdateConverter.toEntity().apply(dto));
        return super.update(status);
    }

}
