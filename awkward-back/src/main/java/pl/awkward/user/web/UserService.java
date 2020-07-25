package pl.awkward.user.web;

import org.springframework.data.domain.Page;
import pl.awkward.user.model_repo.User;

public interface UserService {
    Page<User> getAllWithFilter(int page, int size, String column, String direction, String filter);

    void acceptableEmailAndLogin(String email, String login);

    boolean update(Long id, User user);

    boolean updatePassword(Long id, User userPass);

    boolean updateRoleId(Long id, User userRole);

    void createFolderViaId(Long id);

    void refreshUsersAge();
}
