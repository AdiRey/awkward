package pl.awkward.university.web;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.awkward.address.model_repo.Address;
import pl.awkward.address.model_repo.AddressRepository;
import pl.awkward.shared.baseStuff.BaseConverter;
import pl.awkward.shared.baseStuff.BaseCrudController;
import pl.awkward.shared.baseStuff.BaseRepository;
import pl.awkward.university.dtos.UniversityCreateUpdateDto;
import pl.awkward.university.dtos.UniversityDto;
import pl.awkward.university.dtos.UniversityShowDto;
import pl.awkward.university.model_repo.University;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/universities")
public class UniversityController extends BaseCrudController<University> {

    private final BaseConverter<University, UniversityDto> universityConverter;

    private final BaseConverter<University, UniversityShowDto> universityShowConverter;

    private final BaseConverter<University, UniversityCreateUpdateDto> universityCreateUpdateConverter;

    private final AddressRepository addressRepository;

    private final UniversityService universityService;


    public UniversityController(final BaseRepository<University> universityRepository,
                                final BaseConverter<University, UniversityDto> universityConverter,
                                final BaseConverter<University, UniversityShowDto> universityShowConverter,
                                final BaseConverter<University, UniversityCreateUpdateDto> universityCreateUpdateConverter,
                                final AddressRepository addressRepository,
                                final UniversityService universityService) {
        super(universityRepository);
        this.universityConverter = universityConverter;
        this.universityShowConverter = universityShowConverter;
        this.universityCreateUpdateConverter = universityCreateUpdateConverter;
        this.addressRepository = addressRepository;
        this.universityService = universityService;
    }

    /* ### GET ### */

    @GetMapping("")
    public ResponseEntity<Page<UniversityShowDto>> getAll(@RequestParam(defaultValue = "0") final int page,
                                                          @RequestParam(defaultValue = "20") final int size,
                                                          @RequestParam(defaultValue = "id") final String column,
                                                          @RequestParam(defaultValue = "ASC") final String direction,
                                                          @RequestParam(defaultValue = "") final String filter) {
        if (filter.equals(""))
            return super.getAllByActiveTrue(page, size, column, direction, this.universityShowConverter.toDto());
        return ResponseEntity.ok(
                this.universityService.getAllWithFilterByActiveTrue(page, size, column, direction, filter)
                        .map(this.universityShowConverter.toDto())
        );
    }

    @GetMapping("/allData")
    public ResponseEntity<Page<UniversityDto>> getAllData(@RequestParam(defaultValue = "0") final int page,
                                                          @RequestParam(defaultValue = "20") final int size,
                                                          @RequestParam(defaultValue = "id") final String column,
                                                          @RequestParam(defaultValue = "ASC") final String direction,
                                                          @RequestParam(defaultValue = "") final String filter) {
        if (filter.equals(""))
            return super.getAll(page, size, column, direction, this.universityConverter.toDto());
        return ResponseEntity.ok(
                this.universityService.getAllWithFilter(page, size, column, direction, filter)
                        .map(this.universityConverter.toDto())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UniversityShowDto> getOne(@PathVariable final Long id) {
        return super.getOneByActiveTrue(id, this.universityShowConverter.toDto());
    }

    @GetMapping("/{id}/allData")
    public ResponseEntity<UniversityDto> getOneData(@PathVariable final Long id) {
        return super.getOne(id, this.universityConverter.toDto());
    }

    /* ### POST ### */

    @PostMapping("")
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public ResponseEntity<Void> create(@RequestBody @Valid final UniversityCreateUpdateDto dto) {
        Optional<Address> optional = this.addressRepository.findById(dto.getAddressId());

        if (optional.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Adres nie istnieje.");

        dto.setAddress(optional.get());

        return super.create(dto, this.universityCreateUpdateConverter.toEntity());
    }

    /* ### DELETE ### */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        return super.delete(id);
    }

    /* ### PUT ### */

    @PutMapping("/{id}")
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public ResponseEntity<Void> update(@PathVariable final Long id, @RequestBody @Valid final UniversityCreateUpdateDto dto) {
        Optional<Address> optional = this.addressRepository.findById(dto.getAddressId());

        if (optional.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Adres nie istnieje.");

        dto.setAddress(optional.get());

        boolean status = this.universityService.update(id, this.universityCreateUpdateConverter.toEntity().apply(dto));
        return super.update(status);
    }
}
