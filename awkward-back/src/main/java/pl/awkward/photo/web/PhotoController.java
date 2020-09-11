package pl.awkward.photo.web;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.awkward.photo.dtos.PhotoDto;
import pl.awkward.photo.dtos.PhotoShowDto;
import pl.awkward.photo.model_repo.Photo;
import pl.awkward.shared.baseStuff.BaseConverter;
import pl.awkward.shared.baseStuff.BaseCrudController;
import pl.awkward.shared.baseStuff.BaseRepository;

@RestController
@RequestMapping("/api/photos")
public class PhotoController extends BaseCrudController<Photo> {

    private final BaseConverter<Photo, PhotoDto> photoConverter;

    private final BaseConverter<Photo, PhotoShowDto> photoShowConverter;

    private final PhotoService photoService;

    public PhotoController(final BaseRepository<Photo> repository,
                           final BaseConverter<Photo, PhotoDto> photoConverter,
                           final BaseConverter<Photo, PhotoShowDto> photoShowConverter,
                           final PhotoService photoService) {
        super(repository);
        this.photoConverter = photoConverter;
        this.photoShowConverter = photoShowConverter;
        this.photoService = photoService;
    }

    /* ### GET ### */

    @GetMapping("")
    public ResponseEntity<Page<PhotoShowDto>> getAll(@RequestParam(defaultValue = "0") final int page,
                                                 @RequestParam(defaultValue = "20") final int size,
                                                 @RequestParam(defaultValue = "id") final String column,
                                                 @RequestParam(defaultValue = "ASC") final String direction) {
        return super.getAllByActiveTrue(page, size, column, direction, this.photoShowConverter.toDto());
    }

    @GetMapping("/allData")
    public ResponseEntity<Page<PhotoDto>> getAllData(@RequestParam(defaultValue = "0") final int page,
                                                     @RequestParam(defaultValue = "20") final int size,
                                                     @RequestParam(defaultValue = "id") final String column,
                                                     @RequestParam(defaultValue = "ASC") final String direction) {
        return super.getAll(page, size, column, direction, this.photoConverter.toDto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhotoShowDto> getOne(@PathVariable final Long id) {
        return super.getOneByActiveTrue(id, this.photoShowConverter.toDto());
    }

    @GetMapping("/{id}/allData")
    public ResponseEntity<PhotoDto> getOneData(@PathVariable final Long id) {
        return super.getOne(id, this.photoConverter.toDto());
    }

    /* ### DELETE ### */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        return super.delete(id);
    }

    /* ### PATCH ### */

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateArchive(@PathVariable final Long id, @RequestParam boolean isActive) {
        return super.update(this.photoService.updateArchive(id, isActive));
    }

}
