package pl.awkward.photo.web;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.awkward.photo.dtos.PhotoDto;
import pl.awkward.photo.model_repo.Photo;
import pl.awkward.shared.BaseConverter;
import pl.awkward.shared.BaseCrudController;
import pl.awkward.shared.BaseRepository;

@RestController
@RequestMapping("/api/photos")
public class PhotoController extends BaseCrudController<Photo> {

    private final BaseConverter<Photo, PhotoDto> photoConverter;
    private final PhotoService photoService;

    public PhotoController(BaseRepository<Photo> repository,
                           BaseConverter<Photo, PhotoDto> photoConverter,
                           PhotoService photoService) {
        super(repository);
        this.photoConverter = photoConverter;
        this.photoService = photoService;
    }

    @GetMapping("")
    public ResponseEntity<Page<PhotoDto>> getAll(@RequestParam(defaultValue = "0") final int page,
                                                 @RequestParam(defaultValue = "20") final int size,
                                                 @RequestParam(defaultValue = "id") final String column,
                                                 @RequestParam(defaultValue = "ASC") final String direction) {
        return super.getAll(page, size, column, direction, this.photoConverter.toDto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhotoDto> getOne(@PathVariable final Long id) {
        return super.getOne(id, this.photoConverter.toDto());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        return super.delete(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateActive(@PathVariable final Long id, @RequestParam boolean isActive) {
        if (this.photoService.updateActive(id, isActive))
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
}
