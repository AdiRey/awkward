package pl.awkward.businessCard.web;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.awkward.businessCard.dtos.BusinessCardCreateDto;
import pl.awkward.businessCard.dtos.BusinessCardDto;
import pl.awkward.businessCard.dtos.BusinessCardShowDto;
import pl.awkward.businessCard.dtos.BusinessCardUpdateDto;
import pl.awkward.businessCard.model_repo.BusinessCard;
import pl.awkward.shared.BaseConverter;
import pl.awkward.shared.BaseCrudController;
import pl.awkward.shared.BaseRepository;
import pl.awkward.user.model_repo.User;
import pl.awkward.user.model_repo.UserRepository;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/businessCards")
public class BusinessCardController extends BaseCrudController<BusinessCard> {

    private final BaseConverter<BusinessCard, BusinessCardDto> businessCardConverter;

    private final BaseConverter<BusinessCard, BusinessCardCreateDto> businessCardCreateConverter;

    private final BaseConverter<BusinessCard, BusinessCardUpdateDto> businessCardUpdateConverter;

    private final BaseConverter<BusinessCard, BusinessCardShowDto> businessCardShowConverter;

    private final UserRepository userRepository;

    private final BusinessCardService businessCardService;

    public BusinessCardController(final BaseRepository<BusinessCard> repository,
                                  final BaseConverter<BusinessCard, BusinessCardDto> businessCardConverter,
                                  final BaseConverter<BusinessCard, BusinessCardCreateDto> businessCardCreateConverter,
                                  final BaseConverter<BusinessCard, BusinessCardUpdateDto> businessCardUpdateConverter,
                                  final BaseConverter<BusinessCard, BusinessCardShowDto> businessCardShowConverter,
                                  final UserRepository userRepository,
                                  final BusinessCardService businessCardService) {
        super(repository);
        this.businessCardConverter = businessCardConverter;
        this.businessCardCreateConverter = businessCardCreateConverter;
        this.businessCardUpdateConverter = businessCardUpdateConverter;
        this.businessCardShowConverter = businessCardShowConverter;
        this.userRepository = userRepository;
        this.businessCardService = businessCardService;
    }

    /* ### GET ### */

    @GetMapping("")
    public ResponseEntity<Page<BusinessCardShowDto>> getAll(@RequestParam(defaultValue = "0") final int page,
                                                   @RequestParam(defaultValue = "20") final int size,
                                                   @RequestParam(defaultValue = "id") final String column,
                                                   @RequestParam(defaultValue = "ASC") final String direction) {
        return super.getAllByActiveTrue(page, size, column, direction, this.businessCardShowConverter.toDto());
    }

    @GetMapping("/allData")
    public ResponseEntity<Page<BusinessCardDto>> getAllData(@RequestParam(defaultValue = "0") final int page,
                                                        @RequestParam(defaultValue = "20") final int size,
                                                        @RequestParam(defaultValue = "id") final String column,
                                                        @RequestParam(defaultValue = "ASC") final String direction) {
        return super.getAll(page, size, column, direction, this.businessCardConverter.toDto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusinessCardShowDto> getOne(@PathVariable(name = "id") final Long id) {
        return super.getOneByActiveTrue(id, this.businessCardShowConverter.toDto());
    }

    @GetMapping("/{id}/allData")
    public ResponseEntity<BusinessCardDto> getOneData(@PathVariable(name = "id") final Long id) {
        return super.getOne(id, this.businessCardConverter.toDto());
    }

    /* ### POST ### */

    @PostMapping("")
    public ResponseEntity<Void> create(@RequestBody @Valid final BusinessCardCreateDto dto) {
        Optional<User> optional = this.userRepository.findById(dto.getUserId());

        if (optional.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Użytkownik nie istnieje.");

        dto.setUser(optional.get());

        return super.create(dto, this.businessCardCreateConverter.toEntity());
    }

    /* ### DELETE ### */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        return super.delete(id);
    }

    /* ### PUT ### */

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable final Long id, @RequestBody @Valid final BusinessCardUpdateDto dto) {
        boolean status = this.businessCardService.update(id, this.businessCardUpdateConverter.toEntity().apply(dto));
        return super.update(status);
    }
}
