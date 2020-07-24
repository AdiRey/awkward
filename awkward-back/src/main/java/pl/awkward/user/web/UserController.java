package pl.awkward.user.web;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.awkward.gender.Gender;
import pl.awkward.gender.GenderRepository;
import pl.awkward.liked.dtos.LikedCreateDto;
import pl.awkward.liked.model_repo.Liked;
import pl.awkward.liked.web.LikedService;
import pl.awkward.photo.dtos.PhotoDto;
import pl.awkward.photo.model_repo.Photo;
import pl.awkward.photo.web.PhotoService;
import pl.awkward.shared.BaseConverter;
import pl.awkward.shared.BaseCrudController;
import pl.awkward.shared.BaseRepository;
import pl.awkward.university.model_repo.University;
import pl.awkward.university.model_repo.UniversityRepository;
import pl.awkward.user.dtos.*;
import pl.awkward.user.model_repo.User;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/users")
public class UserController extends BaseCrudController<User> {
    private final BaseConverter<User, UserDto> userConverter;
    private final BaseConverter<User, UserCreateDto> userCreateConverter;
    private final BaseConverter<User, UserUpdateDto> userUpdateConverter;
    private final BaseConverter<User, UserPasswordDto> userPasswordConverter;
    private final BaseConverter<User, UserRoleDto> userRoleConverter;
    private final UserService userService;
    private final PhotoService photoService;
    private final GenderRepository genderRepository;
    private final UniversityRepository universityRepository;
    private final BaseConverter<Photo, PhotoDto> photoConverter;
    private final PasswordEncoder passwordEncoder;
    private final BaseConverter<Liked, LikedCreateDto> likedCreateConverter;
    private final LikedService likedService;

    public UserController(final BaseRepository<User> userRepository,
                          final BaseConverter<User, UserDto> userConverter,
                          final BaseConverter<User, UserCreateDto> userCreateConverter,
                          final BaseConverter<User, UserUpdateDto> userUpdateConverter,
                          final BaseConverter<User, UserPasswordDto> userPasswordConverter,
                          final BaseConverter<User, UserRoleDto> userRoleConverter,
                          final UserService userService,
                          final PhotoService photoService,
                          final GenderRepository genderRepository,
                          final UniversityRepository universityRepository,
                          final BaseConverter<Photo, PhotoDto> photoConverter,
                          final PasswordEncoder passwordEncoder,
                          final BaseConverter<Liked, LikedCreateDto> likedCreateConverter,
                          final LikedService likedService) {
        super(userRepository);
        this.userConverter = userConverter;
        this.userCreateConverter = userCreateConverter;
        this.userUpdateConverter = userUpdateConverter;
        this.userPasswordConverter = userPasswordConverter;
        this.userRoleConverter = userRoleConverter;
        this.userService = userService;
        this.photoService = photoService;
        this.genderRepository = genderRepository;
        this.universityRepository = universityRepository;
        this.photoConverter = photoConverter;
        this.passwordEncoder = passwordEncoder;
        this.likedCreateConverter = likedCreateConverter;
        this.likedService = likedService;
    }

    @GetMapping("")
    public ResponseEntity<Page<UserDto>> getAll(@RequestParam(defaultValue = "0") final int page,
                                                @RequestParam(defaultValue = "20") final int size,
                                                @RequestParam(defaultValue = "id") final String column,
                                                @RequestParam(defaultValue = "ASC") final String direction,
                                                @RequestParam(defaultValue = "") final String filter) {
        if (filter.equals(""))
            return super.getAll(page, size, column, direction, this.userConverter.toDto());
        return ResponseEntity.ok(
                this.userService.getAllWithFilter(page, size, column, direction, filter)
                        .map(this.userConverter.toDto())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getOne(@PathVariable final Long id) {
        return super.getOne(id, this.userConverter.toDto());
    }

    @PostMapping("")
    public ResponseEntity<Void> create(@RequestBody @Valid UserCreateDto dto) {
        this.userService.acceptableEmailAndLogin(dto.getEmail(), dto.getLogin());

        Optional<Gender> optionalGender = this.genderRepository.findById(dto.getGenderId());
        Optional<University> optionalUniversity = this.universityRepository.findById(dto.getUniversityId());
        if (optionalGender.isEmpty() || optionalUniversity.isEmpty())
            throw new IllegalArgumentException("University or gender doesn't exist.");

        dto.setAge(Period.between(dto.getDateOfBirth(), LocalDate.now()).getYears());
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));

        ResponseEntity<Void> voidResponseEntity = super.create(dto, this.userCreateConverter.toEntity());
        try {
            String[] split = Objects.requireNonNull(voidResponseEntity.getHeaders().getLocation()).getPath().split("/");
            this.userService.createFolderViaId(Long.parseLong(split[3]));
        } catch (NullPointerException ex) {
            throw new IllegalArgumentException("Unexpected error, please contact us.");
        }
        return voidResponseEntity;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        return super.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable final Long id, @RequestBody @Valid final UserUpdateDto dto) {
        this.userService.acceptableEmailAndLogin(dto.getEmail(), dto.getLogin());
        boolean status = this.userService.update(id, this.userUpdateConverter.toEntity().apply(dto));
        return super.update(status);
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<Void> updatePassword(@PathVariable final Long id, @RequestBody @Valid final UserPasswordDto dto) {
        if (this.userService.updatePassword(id, this.userPasswordConverter.toEntity().apply(dto)))
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/role")
    public ResponseEntity<Void> updateRole(@PathVariable final Long id, @RequestBody @Valid final UserRoleDto dto) {
        if (this.userService.updateRoleId(id, this.userRoleConverter.toEntity().apply(dto)))
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }

    //photos

    @GetMapping("/{id}/photos")
    public ResponseEntity<Page<PhotoDto>> getAllPhotos(@PathVariable final Long id,
                                                       @RequestParam(defaultValue = "0") final int page,
                                                       @RequestParam(defaultValue = "20") final int size,
                                                       @RequestParam(defaultValue = "true") final boolean active) {
        Page<Photo> allByUserId = this.photoService.getAllByUserId(id, page, size, active);
        return ResponseEntity.ok(allByUserId.map(this.photoConverter.toDto()));
    }

    @PostMapping("/{id}/photos")
    public ResponseEntity<Void> createPhoto(@PathVariable final Long id,
                                            @RequestParam("imageFile") final MultipartFile imageFile) {
        final String content = imageFile.getContentType();
        if (content == null || (!content.equals("image/jpg") && !content.equals("image/png") && !content.equals("image/jpeg")))
            throw new IllegalArgumentException("Unrecognized image format.");
        final Photo photo = this.photoService.save(id, imageFile);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{fileName}")
                .buildAndExpand(photo.getPath().split("/")[2]).toUri();
        return ResponseEntity.created(location).contentType(MediaType.parseMediaType(content)).build();
    }

    @GetMapping("/{id}/photos/{filename}")
    public ResponseEntity<PhotoDto> getOnePhoto(@PathVariable final Long id, @PathVariable final String filename) {
        Optional<Photo> optionalPhoto = this.photoService.getOnePhoto(id, filename);
        return optionalPhoto
                .map(p -> ResponseEntity.ok(this.photoConverter.toDto().apply(p)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //liked

    @PostMapping("/{id}/liked")
    public ResponseEntity<Void> createLike(@PathVariable final Long id, @RequestBody @Valid final LikedCreateDto dto) {
        Liked liked = this.likedCreateConverter.toEntity().apply(dto);
        liked.setUserId(id);
        final Liked saved = this.likedService.save(liked);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
                .buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
