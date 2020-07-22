package pl.awkward.businessCard.web;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.awkward.businessCard.dtos.BusinessCardCreateDto;
import pl.awkward.businessCard.dtos.BusinessCardDto;
import pl.awkward.businessCard.dtos.BusinessCardUpdateDto;
import pl.awkward.businessCard.model_repo.BusinessCard;
import pl.awkward.shared.BaseConverter;
import pl.awkward.shared.BaseCrudController;
import pl.awkward.shared.BaseRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/businessCards")
public class BusinessCardController extends BaseCrudController<BusinessCard> {

    private final BaseConverter<BusinessCard, BusinessCardDto> businessCardConverter;
    private final BaseConverter<BusinessCard, BusinessCardCreateDto> businessCardCreateConverter;
    private final BaseConverter<BusinessCard, BusinessCardUpdateDto> businessCardUpdateConverter;
    private final BusinessCardService businessCardService;

    public BusinessCardController(BaseRepository<BusinessCard> repository,
                                  BaseConverter<BusinessCard, BusinessCardDto> businessCardConverter,
                                  BaseConverter<BusinessCard, BusinessCardCreateDto> businessCardCreateConverter,
                                  BaseConverter<BusinessCard, BusinessCardUpdateDto> businessCardUpdateConverter,
                                  BusinessCardService businessCardService) {
        super(repository);
        this.businessCardConverter = businessCardConverter;
        this.businessCardCreateConverter = businessCardCreateConverter;
        this.businessCardUpdateConverter = businessCardUpdateConverter;
        this.businessCardService = businessCardService;
    }

    @GetMapping("")
    public ResponseEntity<Page<BusinessCardDto>> getAll(@RequestParam(defaultValue = "0") final int page,
                                                   @RequestParam(defaultValue = "20") final int size,
                                                   @RequestParam(defaultValue = "id") final String column,
                                                   @RequestParam(defaultValue = "ASC") final String direction) {
        return super.getAll(page, size, column, direction, businessCardConverter.toDto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusinessCardDto> getOne(@PathVariable(name = "id") final Long id) {
        return super.getOne(id, businessCardConverter.toDto());
    }

    @PostMapping("")
    public ResponseEntity<Void> create(@RequestBody @Valid final BusinessCardCreateDto dto) {
        return super.create(dto, businessCardCreateConverter.toEntity());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        return super.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable final Long id, @RequestBody @Valid final BusinessCardUpdateDto dto) {
        boolean status = this.businessCardService.update(id, businessCardUpdateConverter.toEntity().apply(dto));
        return super.update(status);
    }
}
