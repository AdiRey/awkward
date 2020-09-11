package pl.awkward.address.web;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.awkward.address.dtos.AddressDto;
import pl.awkward.address.dtos.AddressShowDto;
import pl.awkward.address.model_repo.Address;
import pl.awkward.shared.baseStuff.BaseConverter;
import pl.awkward.shared.baseStuff.BaseCrudController;
import pl.awkward.shared.baseStuff.BaseRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/addresses")
public class AddressController extends BaseCrudController<Address> {

    private final BaseConverter<Address, AddressDto> addressConverter;

    private final BaseConverter<Address, AddressShowDto> addressShowConverter;

    private final AddressService addressService;


    public AddressController(final BaseRepository<Address> repository,
                             final BaseConverter<Address, AddressDto> addressConverter,
                             final BaseConverter<Address, AddressShowDto> addressShowConverter,
                             final AddressService addressService) {
        super(repository);
        this.addressConverter = addressConverter;
        this.addressService = addressService;
        this.addressShowConverter = addressShowConverter;
    }

    /* ### GET ### */

    @GetMapping("")
    public ResponseEntity<Page<AddressShowDto>> getAll(@RequestParam(defaultValue = "0") final int page,
                                                   @RequestParam(defaultValue = "20") final int size,
                                                   @RequestParam(defaultValue = "id") final String column,
                                                   @RequestParam(defaultValue = "ASC") final String direction,
                                                   @RequestParam(defaultValue = "") final String filter) {
        if (filter.equals(""))
            return super.getAllByActiveTrue(page, size, column, direction, this.addressShowConverter.toDto());
        return ResponseEntity
                .ok(this.addressService.getAllWithFilterAndActiveTrue(page, size, column, direction, filter)
                        .map(this.addressShowConverter.toDto())
                );
    }

    @GetMapping("/allData")
    public ResponseEntity<Page<AddressDto>> getAllData(@RequestParam(defaultValue = "0") final int page,
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
    public ResponseEntity<AddressShowDto> getOne(@PathVariable final Long id) {
        return super.getOneByActiveTrue(id, this.addressShowConverter.toDto());
    }

    @GetMapping("/{id}/allData")
    public ResponseEntity<AddressDto> getOneData(@PathVariable final Long id) {
        return super.getOne(id, this.addressConverter.toDto());
    }

    /* ### POST ### */

    @PostMapping("")
    public ResponseEntity<Void> create(@RequestBody @Valid final Address address) {
        return super.create(address);
    }

    /* ### DELETE ### */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        return super.delete(id);
    }

    /* ### PUT ### */

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable final Long id, @RequestBody @Valid final Address address) {
        boolean status = this.addressService.update(id, address);
        return super.update(status);
    }

}
