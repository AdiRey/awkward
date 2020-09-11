package pl.awkward.role.web;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.awkward.role.dtos.RoleDto;
import pl.awkward.role.dtos.RoleShowDto;
import pl.awkward.role.model_repo.Role;
import pl.awkward.shared.baseStuff.BaseConverter;
import pl.awkward.shared.baseStuff.BaseCrudController;
import pl.awkward.shared.baseStuff.BaseRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/roles")
public class RoleController extends BaseCrudController<Role> {

    private final BaseConverter<Role, RoleDto> roleConverter;

    private final BaseConverter<Role, RoleShowDto> roleShowConverter;

    private final RoleService roleService;


    public RoleController(final BaseRepository<Role> roleRepository,
                          final BaseConverter<Role, RoleDto> roleConverter,
                          final BaseConverter<Role, RoleShowDto> roleShowConverter,
                          final RoleService roleService) {
        super(roleRepository);
        this.roleConverter = roleConverter;
        this.roleShowConverter = roleShowConverter;
        this.roleService = roleService;
    }

    /* ### GET ### */

    @GetMapping("")
    public ResponseEntity<Page<RoleShowDto>> getAll(@RequestParam(defaultValue = "0") final int page,
                                                   @RequestParam(defaultValue = "20") final int size,
                                                   @RequestParam(defaultValue = "id") final String column,
                                                   @RequestParam(defaultValue = "ASC") final String direction) {
        return super.getAllByActiveTrue(page, size, column, direction, this.roleShowConverter.toDto());
    }

    @GetMapping("/allData")
    public ResponseEntity<Page<RoleDto>> getAllData(@RequestParam(defaultValue = "0") final int page,
                                                @RequestParam(defaultValue = "20") final int size,
                                                @RequestParam(defaultValue = "id") final String column,
                                                @RequestParam(defaultValue = "ASC") final String direction) {
        return super.getAll(page, size, column, direction, this.roleConverter.toDto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleShowDto> getOne(@PathVariable final Long id) {
        return super.getOneByActiveTrue(id, this.roleShowConverter.toDto());
    }

    @GetMapping("/{id}/allData")
    public ResponseEntity<RoleDto> getOneData(@PathVariable final Long id) {
        return super.getOne(id, this.roleConverter.toDto());
    }

    /* ### POST ### */

    @PostMapping("")
    public ResponseEntity<Void> create(@RequestBody @Valid final Role role) {
        return super.create(role);
    }

    /* ### DELETE ### */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        return super.delete(id);
    }

    /* ### PUT ### */

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable final Long id, @RequestBody @Valid final Role role) {
        boolean status = this.roleService.update(id, role);
        return super.update(status);
    }
}
