package pl.awkward.user_address.web;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.awkward.shared.BaseConverter;
import pl.awkward.shared.BaseCrudController;
import pl.awkward.shared.BaseRepository;
import pl.awkward.user_address.dtos.UserAddressCreateDto;
import pl.awkward.user_address.dtos.UserAddressDto;
import pl.awkward.user_address.model_repo.UserAddress;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/userAddresses")
public class UserAddressController extends BaseCrudController<UserAddress> {

    private final BaseConverter<UserAddress, UserAddressDto> uaConverter;
    private final BaseConverter<UserAddress, UserAddressCreateDto> uaCreateConverter;
    private final UserAddressService userAddressService;


    public UserAddressController(BaseRepository<UserAddress> repository,
                                 BaseConverter<UserAddress, UserAddressDto> uaConverter,
                                 BaseConverter<UserAddress, UserAddressCreateDto> uaCreateConverter,
                                 UserAddressService userAddressService) {
        super(repository);
        this.uaConverter = uaConverter;
        this.uaCreateConverter = uaCreateConverter;
        this.userAddressService = userAddressService;
    }

    @GetMapping("")
    public ResponseEntity<Page<UserAddressDto>> getAll(@RequestParam(defaultValue = "0") final int page,
                                                        @RequestParam(defaultValue = "20") final int size,
                                                        @RequestParam(defaultValue = "id") final String column,
                                                        @RequestParam(defaultValue = "ASC") final String direction) {
        return super.getAll(page, size, column, direction, uaConverter.toDto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAddressDto> getOne(@PathVariable(name = "id") final Long id) {
        return super.getOneByActiveTrue(id, uaConverter.toDto());
    }

    @PostMapping("")
    public ResponseEntity<Void> create(@RequestBody @Valid final UserAddressCreateDto dto) {
        this.userAddressService.acceptableIds(dto.getUserId(), dto.getAddressId());
        return super.create(dto, uaCreateConverter.toEntity());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        return super.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable final Long id, @RequestBody @Valid UserAddressCreateDto dto) {
        boolean status = this.userAddressService.update(id, uaCreateConverter.toEntity().apply(dto));
        return super.update(status);
    }

}
