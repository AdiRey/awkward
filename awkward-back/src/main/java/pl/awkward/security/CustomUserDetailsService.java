package pl.awkward.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.awkward.role.model_repo.RoleRepository;
import pl.awkward.user.model_repo.User;
import pl.awkward.user.model_repo.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = this.userRepository.findFirstByEmailOrLogin(username, username);

        if (optionalUser.isEmpty())
            throw new UsernameNotFoundException("User not found");

        User user = optionalUser.get();

        return new org.springframework.security.core.userdetails.User(
                user.getId() +"-" + user.getLogin(),
                user.getPassword(),
                getAuthorities(user.getRoleId())
        );
    }

    List<GrantedAuthority> getAuthorities(final Long id) {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(String.valueOf(id)));
        return list;
    }
}
