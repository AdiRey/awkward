package pl.awkward.user.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.awkward.exceptions.DuplicateException;
import pl.awkward.gender.Gender;
import pl.awkward.university.model_repo.University;
import pl.awkward.user.model_repo.User;
import pl.awkward.user.model_repo.UserRepository;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements pl.awkward.user.web.UserService {
    private final UserRepository userRepository;
    private static final String PATH_TO_USER_DIR = "user_images/";

    @Override
    public Page<User> getAllWithFilter(int page, int size, String column, String direction, String filter) {
        Sort.Direction sortDir = direction.equals("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(new Sort.Order(sortDir, column));
        return this.userRepository
                .findAllByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(filter, filter, PageRequest.of(page, size, sort));
    }

    @Override
    public void acceptableEmailAndLogin(final String email, final String login) {
        Optional<User> user = this.userRepository.findFirstByEmailOrLogin(email, login);
        user.ifPresent(
                u -> {
                    if (email.equals(u.getEmail()))
                        throw new DuplicateException("User with that email already exists.");
                    else
                        throw new DuplicateException("User with that login already exists");
                }
        );
    }


    @Override
    @Transactional
    public boolean update(final Long id, final User updateUser) {
        Optional<User> optionalUser = this.userRepository.findById(id);
        if (optionalUser.isEmpty())
            return false;
        User user = optionalUser.get();
        user.setEmail(updateUser.getEmail());
        user.setLogin(updateUser.getLogin());
        user.setName(updateUser.getName());
        user.setSurname(updateUser.getSurname());
        user.setDateOfBirth(updateUser.getDateOfBirth());
        user.setDescription(updateUser.getDescription());
        user.setUniversityId(updateUser.getUniversityId());
        return true;
    }

    @Override
    @Transactional
    public boolean updatePassword(final Long id, User user) {
        Optional<User> userById = this.userRepository.findById(id);
        if (userById.isPresent()) {
            userById.get().setPassword(user.getPassword());
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean updateRoleId(Long id, User userRole) {
        Optional<User> userById = this.userRepository.findById(id);
        if (userById.isPresent()) {
            userById.get().setRoleId(userRole.getRoleId());
            return true;
        }
        return false;
    }

    @Override
    public void createFolderViaId(final Long id) {
        final File file = new File(PATH_TO_USER_DIR + id);
        if (!file.mkdir())
            throw new IllegalArgumentException("There is a problem, contact admin.");
    }

    @Override
    @Transactional
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
