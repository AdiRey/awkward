package pl.awkward.user.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import pl.awkward.user.model_repo.User;
import pl.awkward.user.model_repo.UserRepository;

import java.io.File;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements pl.awkward.user.web.UserService {

    private static final String PATH_TO_USER_DIR = "awkward-back/user_images/";

    private final UserRepository userRepository;

    @Override
    public Integer getAmountOfUsers() {
        return this.userRepository.getCount();
    }

    @Override
    public Page<User> getAllWithFilter(int page, int size, String column, String direction, String filter) {

        Sort.Direction sortDir = direction.equals("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(new Sort.Order(sortDir, column));

        return this.userRepository
                .findAllByNameOrSurnameContains(
                        filter,
                        filter,
                        PageRequest.of(page, size, sort)
                );
    }

    @Override
    public Page<User> getAllWithFilterByActiveTrue(int page, int size, String column, String direction, String filter) {

        Sort.Direction sortDir = direction.equals("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(new Sort.Order(sortDir, column));

        return this.userRepository
                .findAllByNameOrSurnameContainsAndActiveIsTrue(
                        filter,
                        filter,
                        PageRequest.of(page, size, sort)
                );
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public boolean update(final Long id, final User updateUser) {

        Optional<User> optionalUser = this.userRepository.findById(id);

        if (optionalUser.isEmpty())
            return false;

        User user = optionalUser.get();

        user.setAge(Period.between(user.getDateOfBirth(), LocalDate.now()).getYears());

        user.setEmail(updateUser.getEmail());
        user.setUsername(updateUser.getUsername());

        user.setName(updateUser.getName());
        user.setSurname(updateUser.getSurname());

        user.setDateOfBirth(updateUser.getDateOfBirth());
        user.setAge(updateUser.getAge());
        user.setDescription(updateUser.getDescription());

        user.setGender(updateUser.getGender());
        user.setUniversity(updateUser.getUniversity());

        return true;
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public boolean updatePassword(final Long id, User user) {

        Optional<User> userById = this.userRepository.findById(id);

        if (userById.isPresent()) {
            userById.get().setPassword(user.getPassword());
            return true;
        }

        return false;
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public boolean updateRole(Long id, User userRole) {

        Optional<User> userById = this.userRepository.findById(id);

        if (userById.isPresent()) {
            userById.get().setRole(userRole.getRole());
            return true;
        }

        return false;
    }

    @Override
    public void createFolderViaId(final Long id) {
        final File file = new File(PATH_TO_USER_DIR + id);

        if (!file.exists())
            file.mkdir();
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void refreshUsersAge() {

        List<User> all = this.userRepository.findAll();

        all
                .parallelStream()
                .forEach(u -> {
                    final int years = Period.between(u.getDateOfBirth(), LocalDate.now()).getYears();
                    if (years != u.getAge())
                        u.setAge(Period.between(u.getDateOfBirth(), LocalDate.now()).getYears());
                });
    }
}
