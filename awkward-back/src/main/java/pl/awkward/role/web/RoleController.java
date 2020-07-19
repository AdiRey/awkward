package pl.awkward.role.web;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.awkward.role.dtos.RoleCreateUpdateDto;
import pl.awkward.role.dtos.RoleDto;
import pl.awkward.role.model_repo.Role;
import pl.awkward.shared.BaseConverter;
import pl.awkward.shared.BaseCrudController;
import pl.awkward.shared.BaseRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/roles")
public class RoleController extends BaseCrudController<Role> {

    private final BaseConverter<Role, RoleDto> roleConverter;
    private final BaseConverter<Role, RoleCreateUpdateDto> roleCreateConverter;
    private final RoleService roleService;

    public RoleController(BaseRepository<Role> roleRepository,
                          BaseConverter<Role, RoleDto> roleConverter,
                          BaseConverter<Role, RoleCreateUpdateDto> roleCreateConverter,
                          RoleService roleService) {
        super(roleRepository);
        this.roleConverter = roleConverter;
        this.roleCreateConverter = roleCreateConverter;
        this.roleService = roleService;
    }


    @GetMapping("")
    public ResponseEntity<Page<RoleDto>> getAll(@RequestParam(defaultValue = "0") final int page,
                                                   @RequestParam(defaultValue = "20") final int size,
                                                   @RequestParam(defaultValue = "id") final String column,
                                                   @RequestParam(defaultValue = "ASC") final String direction) {
        return super.getAll(page, size, column, direction, roleConverter.toDto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> getOne(@PathVariable final Long id) {
        return super.getOne(id, roleConverter.toDto());
    }

    @PostMapping("")
    public ResponseEntity<Void> create(@RequestBody @Valid final RoleCreateUpdateDto dto) {
        return super.create(dto, roleCreateConverter.toEntity());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        return super.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable final Long id, @RequestBody @Valid final RoleCreateUpdateDto dto) {
        boolean status = this.roleService.update(id, this.roleCreateConverter.toEntity().apply(dto));
        return super.update(status);
    }
}
