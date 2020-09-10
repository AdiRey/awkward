package pl.awkward.user.web;

import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.awkward.exceptions.OperationNotAllowedException;
import pl.awkward.gender.Gender;
import pl.awkward.gender.GenderRepository;
import pl.awkward.liked.dtos.LikedCreateDto;
import pl.awkward.liked.dtos.LikedDto;
import pl.awkward.liked.model_repo.Liked;
import pl.awkward.liked.web.LikedService;
import pl.awkward.photo.dtos.PhotoShowDto;
import pl.awkward.photo.model_repo.Photo;
import pl.awkward.photo.web.PhotoService;
import pl.awkward.role.model_repo.Role;
import pl.awkward.role.model_repo.RoleRepository;
import pl.awkward.shared.ApiList;
import pl.awkward.shared.baseStuff.BaseConverter;
import pl.awkward.shared.baseStuff.BaseCrudController;
import pl.awkward.shared.JsonBuilder;
import pl.awkward.university.model_repo.University;
import pl.awkward.university.model_repo.UniversityRepository;
import pl.awkward.user.dtos.*;
import pl.awkward.user.model_repo.User;
import pl.awkward.user.model_repo.UserRepository;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/users")
@CrossOrigin
public class UserController extends BaseCrudController<User> {

    private final static String USER = "USER";

    private final BaseConverter<User, UserDto> userConverter;

    private final BaseConverter<User, UserCreateDto> userCreateConverter;

    private final BaseConverter<User, UserUpdateDto> userUpdateConverter;

    private final BaseConverter<User, UserPasswordDto> userPasswordConverter;

    private final BaseConverter<User, UserRoleDto> userRoleConverter;

    private final UserService userService;

    private final PhotoService photoService;

    private final BaseConverter<Photo, PhotoShowDto> photoShowConverter;

    private final PasswordEncoder passwordEncoder;

    private final BaseConverter<Liked, LikedDto> likedConverter;

    private final BaseConverter<Liked, LikedCreateDto> likedCreateConverter;

    private final LikedService likedService;

    private final BaseConverter<User, UserShowDto> userShowConverter;

    private final RoleRepository roleRepository;

    private final GenderRepository genderRepository;

    private final UniversityRepository universityRepository;

    private final UserRepository userRepository;

    public UserController(final UserRepository userRepository,
                          final BaseConverter<User, UserDto> userConverter,
                          final BaseConverter<User, UserCreateDto> userCreateConverter,
                          final BaseConverter<User, UserUpdateDto> userUpdateConverter,
                          final BaseConverter<User, UserPasswordDto> userPasswordConverter,
                          final BaseConverter<User, UserRoleDto> userRoleConverter,
                          final UserService userService,
                          final PhotoService photoService,
                          final BaseConverter<Photo, PhotoShowDto> photoShowConverter,
                          final PasswordEncoder passwordEncoder,
                          final BaseConverter<Liked, LikedDto> likedConverter,
                          final BaseConverter<Liked, LikedCreateDto> likedCreateConverter,
                          final LikedService likedService,
                          final BaseConverter<User, UserShowDto> userShowConverter,
                          final RoleRepository roleRepository,
                          final UniversityRepository universityRepository,
                          final GenderRepository genderRepository) {
        super(userRepository);
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.userCreateConverter = userCreateConverter;
        this.userUpdateConverter = userUpdateConverter;
        this.userPasswordConverter = userPasswordConverter;
        this.userRoleConverter = userRoleConverter;
        this.userService = userService;
        this.photoService = photoService;
        this.photoShowConverter = photoShowConverter;
        this.passwordEncoder = passwordEncoder;
        this.likedConverter = likedConverter;
        this.likedCreateConverter = likedCreateConverter;
        this.likedService = likedService;
        this.userShowConverter = userShowConverter;
        this.roleRepository = roleRepository;
        this.genderRepository = genderRepository;
        this.universityRepository = universityRepository;
    }


    /* ### GET ### */


    @GetMapping("/amount")
    public ResponseEntity<Integer> amount() {
        return ResponseEntity.ok(this.userService.getAmountOfUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserShowDto> getOne(@PathVariable final Long id) {
        return super.getOneByActiveTrue(id, this.userShowConverter.toDto());
    }

    @GetMapping("/{id}/allData")
    @Transactional(isolation = Isolation.READ_UNCOMMITTED, readOnly = true)
    public ResponseEntity<UserDto> getOneData(@PathVariable final Long id) {
        return super.getOne(id, this.userConverter.toDto());
    }

    @GetMapping("")
    public ResponseEntity<Page<UserShowDto>> getAll(@RequestParam(defaultValue = "0") final int page,
                                                    @RequestParam(defaultValue = "20") final int size,
                                                    @RequestParam(defaultValue = "id") final String column,
                                                    @RequestParam(defaultValue = "ASC") final String direction,
                                                    @RequestParam(defaultValue = "") final String filter) {
        if (filter.equals(""))
            return super.getAllByActiveTrue(page, size, column, direction, this.userShowConverter.toDto());
        return ResponseEntity.ok(
                this.userService.getAllWithFilterByActiveTrue(page, size, column, direction, filter)
                        .map(this.userShowConverter.toDto())
        );
    }

    @GetMapping("/allData")
    @Transactional(isolation = Isolation.READ_UNCOMMITTED, readOnly = true)
    public ResponseEntity<Page<UserDto>> getAllData(@RequestParam(defaultValue = "0") final int page,
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

    @GetMapping("/{id}/photos")
    public ResponseEntity<Page<PhotoShowDto>> getAllPhotos(@PathVariable final Long id,
                                                           @RequestParam(defaultValue = "0") final int page,
                                                           @RequestParam(defaultValue = "20") final int size,
                                                           @RequestParam(defaultValue = "true") final boolean archive) {
        Page<Photo> allByUserId = this.photoService.getAllByUserId(id, page, size, archive);
        return ResponseEntity.ok(allByUserId.map(this.photoShowConverter.toDto()));
    }

    @GetMapping("/{id}/likes")
    public ResponseEntity<Page<LikedDto>> getAllLikes(@PathVariable Long id,
                                                      @RequestParam(defaultValue = "0") final int page,
                                                      @RequestParam(defaultValue = "20") final int size) {
        Page<Liked> pageLikes = this.likedService.getAllPagination(id, page, size);
        return ResponseEntity.ok(pageLikes.map(likedConverter.toDto()));
    }


    /* ### POST ### */


    @PostMapping("")
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public ResponseEntity<Void> create(@RequestBody @Valid UserCreateDto dto) {

        Optional<Role> optionalRole = this.roleRepository.findByName(USER);
        Optional<Gender> optionalGender = this.genderRepository.findById(dto.getGenderId());

        if (optionalGender.isEmpty() || optionalRole.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rola albo płeć z takim id nie istnieje.");

        dto.setAge(Period.between(dto.getDateOfBirth(), LocalDate.now()).getYears());
        dto.setPassword(this.passwordEncoder.encode(dto.getPassword()));
        dto.setGender(optionalGender.get());
        dto.setRole(optionalRole.get());

        ResponseEntity<Void> voidResponseEntity = super.create(dto, this.userCreateConverter.toEntity());

        try {
            String[] split = Objects.requireNonNull(voidResponseEntity.getHeaders().getLocation()).getPath().split("/");
            this.userService.createFolderViaId(Long.parseLong(split[3]));
        } catch (NullPointerException ex) {
            throw new IllegalArgumentException("Konto utworzone pomyślnie, ale przytrafił się niespodziewany błąd.");
        }
        return voidResponseEntity;
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

    @PostMapping("/liked") //it is possible to like someone who's been already deleted, but: does it matter?
    public ResponseEntity<Void> createLike(@RequestBody @Valid final LikedCreateDto dto) {
        if (dto.getFirstUserId().equals(dto.getSecondUserId()))
            throw new OperationNotAllowedException("Nie możesz dać sam sobie lajka.");

        final Optional<User> first = this.userRepository.findById(dto.getFirstUserId());
        final Optional<User> second = this.userRepository.findById(dto.getSecondUserId());

        if (first.isEmpty() || second.isEmpty()) {
            throw new IllegalArgumentException("Userzy nie istnieja.");
        }

        dto.setFirstUser(first.get());
        dto.setSecondUser(second.get());

        Liked liked = this.likedCreateConverter.toEntity().apply(dto);
        final Liked saved;

        if (!this.likedService.exists(liked))
            saved = this.likedService.save(liked);
        else
            saved = this.likedService.update(liked);



        if (saved.getFirstStatus() != null && saved.getSecondStatus() != null) {
            final RestTemplate restTemplate = new RestTemplate();
            final JsonBuilder<String, Number> jsonBuilder = new JsonBuilder<>();
            final Byte status = (saved.getFirstStatus() > saved.getSecondStatus())? saved.getSecondStatus() : saved.getFirstStatus();

            jsonBuilder.put("firstUserId", saved.getFirstUser().getId());
            jsonBuilder.put("secondUserId", saved.getSecondUser().getId());
            jsonBuilder.put("status", status);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> httpEntity = new HttpEntity<>(jsonBuilder.getJson(), httpHeaders);

            restTemplate.postForObject(
                    ServletUriComponentsBuilder.fromCurrentContextPath().toUriString() + ApiList.PAIR_API.getPath(),
                    httpEntity,
                    Object.class
            );
        }

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(location).build();
    }


    /* ### PUT ### */

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable final Long id, @RequestBody @Valid final UserUpdateDto dto) {

        Optional<Gender> optionalGender = this.genderRepository.findById(dto.getGenderId());
        Optional<University> optionalUniversity = this.universityRepository.findById(dto.getUniversityId());

        if (optionalUniversity.isEmpty() || optionalGender.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Uniwersytet albo płeć z takim id nie istnieje.");

        dto.setAge(Period.between(dto.getDateOfBirth(), LocalDate.now()).getYears());
        dto.setGender(optionalGender.get());
        dto.setUniversity(optionalUniversity.get());

        boolean status = this.userService.update(id, this.userUpdateConverter.toEntity().apply(dto));
        return super.update(status);
    }


    /* ### PATCH ### */


    @PatchMapping("/{id}/password")
    public ResponseEntity<Void> updatePassword(@PathVariable final Long id, @RequestBody @Valid final UserPasswordDto dto) {

        dto.setPassword(this.passwordEncoder.encode(dto.getPassword()));

        if (this.userService.updatePassword(id, this.userPasswordConverter.toEntity().apply(dto)))
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/role")
    public ResponseEntity<Void> updateRole(@PathVariable final Long id, @RequestBody @Valid final UserRoleDto dto) {

        Optional<Role> optionalRole = this.roleRepository.findById(dto.getRoleId());

        if (optionalRole.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rola z takim id nie istnieje.");

        dto.setRole(optionalRole.get());

        if (this.userService.updateRole(id, this.userRoleConverter.toEntity().apply(dto)))
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }


    /* ### DELETE ### */


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        return super.delete(id);
    }

}
