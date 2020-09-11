package pl.awkward.businessCard.web;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.awkward.businessCard.dtos.BusinessCardDto;
import pl.awkward.businessCard.dtos.BusinessCardShowDto;
import pl.awkward.businessCard.model_repo.BusinessCard;
import pl.awkward.shared.baseStuff.BaseConverter;
import pl.awkward.shared.baseStuff.BaseCrudController;
import pl.awkward.shared.baseStuff.BaseRepository;
import pl.awkward.user.model_repo.UserRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/businessCards")
public class BusinessCardController extends BaseCrudController<BusinessCard> {

    private final BaseConverter<BusinessCard, BusinessCardDto> businessCardConverter;

    private final BaseConverter<BusinessCard, BusinessCardShowDto> businessCardShowConverter;

    private final UserRepository userRepository;

    private final BusinessCardService businessCardService;

    public BusinessCardController(final BaseRepository<BusinessCard> repository,
                                  final BaseConverter<BusinessCard, BusinessCardDto> businessCardConverter,
                                  final BaseConverter<BusinessCard, BusinessCardShowDto> businessCardShowConverter,
                                  final UserRepository userRepository,
                                  final BusinessCardService businessCardService) {
        super(repository);
        this.businessCardConverter = businessCardConverter;
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
    public ResponseEntity<Void> create(@RequestBody @Valid final BusinessCard card) {
        return super.create(card);
    }

    /* ### DELETE ### */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        return super.delete(id);
    }

    /* ### PUT ### */

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable final Long id, @RequestBody @Valid final BusinessCard card) {
        boolean status = this.businessCardService.update(id, card);
        return super.update(status);
    }
}
